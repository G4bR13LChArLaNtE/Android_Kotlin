import requests
from flask import Flask, jsonify

from sqlalchemy import create_engine
from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import Session


app = Flask(__name__)

paises = []
c1 = []
p = {}
engine = create_engine("sqlite:///countries.db?check_same_thread=False")
connection = engine.connect()


Base = declarative_base(engine)





connection.execute("""CREATE TABLE IF NOT EXISTS countries (
                       ID INTEGER PRIMARY KEY,
                       NOME VARCHAR(255) NOT NULL,
                       BANDEIRA VARCHAR(255) NOT NULL,
                       CAPITAL VARCHAR(255) NOT NULL,
                       CONTINENTE VARCHAR(255) NOT NULL,
                       POPULACAO VARCHAR(255) NOT NULL,
                       LATITUDE VARCHAR(255) NOT NULL,
                       LONGITUDE VARCHAR(255) NOT NULL)""")


class Countries(Base):
    __tablename__ = 'COUNTRIES'
    id = Column('ID', Integer, primary_key=True, autoincrement=True)
    nome = Column('NOME', String(255), nullable=False)
    bandeira = Column('BANDEIRA', String(255), nullable=False)
    capital = Column('CAPITAL', String(255), nullable=False)
    continente = Column('CONTINENTE', String(255), nullable=False)
    populacao = Column('POPULACAO', String(255), nullable=False)
    latitude = Column('LATITUDE', String(255), nullable=False)
    longitude = Column('LONGITUDE', String(255), nullable=False)
    
    def __init__(self, nome, bandeira, capital, continente, populacao, latitude, longitude):
        self.nome = nome
        self.bandeira = bandeira
        self.capital = capital
        self.continente = continente
        self.populacao = populacao
        self.latitude = latitude
        self.longitude = longitude


@app.route("/paises")
@app.route("/paises", methods=['GET'])
def get():
    c1 = []
    session = Session()
    result = session.query(Countries)
    c = {}
    for i in result:
        c = {"id": i.id, "nome_pais": i.nome, "bandeira": i.bandeira, "capital":i.capital, "continente": i.continente, "populacao": i.populacao, "latitude": i.latitude, "longitude": i.longitude}
        c1.append(c)
    return jsonify(c1)

@app.route("/paises/<int:id>", methods=['GET'])
def get_one(id):
    session = Session()
    paises = session.query(Countries.id, Countries.nome, Countries.bandeira, Countries.capital, Countries.continente, Countries.populacao, Countries.latitude, Countries.longitude)
    for pais in paises:
        if pais.id == id:
            p = {"id": pais.id, "nome": pais.nome, "bandeira": pais.bandeira, "capital": pais.capital, "continente": pais.continente, "latitude": pais.latitude, "longitude": pais.longitude}
            return jsonify(p)
    return "", 404

@app.route("/paises/<string:nome>", methods=['POST'])
def post(nome):
    session = Session()

    url = f'https://restcountries.com/v3.1/name/{nome}'

    resultado = requests.get(url)

    n = resultado.json()
    
    nome_pais = n[0]['translations']['por']['common']

    bandeira = n[0]['flags']['png']

    capital = n[0]['capital'][0]

    continente = n[0]['region']

    populacao = n[0]['population']

    latitude = n[0]['latlng'][0]

    longitude = n[0]['latlng'][1]
    
    
    try:
        content = {'nome': nome_pais, 'bandeira': bandeira, 'capital': capital, 'continente': continente, 'populacao': populacao, 'latitude': latitude, 'longitude': longitude}

        # gerar id
        ids = [e["id"] for e in paises]
        if ids:
            nid = max(ids) + 1
        else:
            nid = 1
        content["id"] = nid
        c = Countries(nome_pais, bandeira, capital, continente, populacao, latitude, longitude)
        session.add(c)
        session.commit()
        return jsonify({"status":"OK", "msg":"País adicionado com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})



@app.route("/paises/<string:nome>", methods=['DELETE'])
def delete(nome):
    session = Session()
    try:
        c = session.query(Countries).filter(Countries.nome==nome).delete()
        print(c)
        session.commit()
        return jsonify({"status":"OK", "msg":"País removido com sucesso"})
    except Exception as ex:
        print(ex)
        return jsonify({"status":"ERRO", "msg":str(ex)})


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)