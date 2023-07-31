package br.com.mobile.infocountries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class PaisesAdapter (
    val paises: List<Pais>,
    val onClick: (Pais) -> Unit):
    RecyclerView.Adapter<PaisesAdapter.PaisesViewHolder>()
    {

        // ViewHolder com os elementos da tela
        class PaisesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val cardNome: TextView
            val cardImg: ImageView
            val cardCapital: TextView
            var cardProgress: ProgressBar
            var cardView: CardView

            init {
                cardNome = view.findViewById<TextView>(R.id.cardNome)
                cardImg = view.findViewById<ImageView>(R.id.cardImg)
                cardCapital =  view.findViewById<TextView>(R.id.cardCapital)
                cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
                cardView = view.findViewById<CardView>(R.id.card_paises)
            }
        }

        // Quantidade de países na lista
        override fun getItemCount() = this.paises.size


        // inflar layout do adapter
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesViewHolder {
            // infla view no adapter
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pais, parent, false)

            // retornar ViewHolder
            val holder = PaisesViewHolder(view)
            return holder
        }


        // bind para atualizar Views com os dados
        override fun onBindViewHolder(holder: PaisesViewHolder, position: Int) {
            val context = holder.itemView.context

            // recuperar objeto pais
            val pais = paises[position]

            // atualizar dados de pais
            holder.cardNome.text = pais.nome_pais

            holder.cardCapital.text = pais.capital
            holder.cardProgress.visibility = View.VISIBLE

            // download da imagem
            Picasso.with(context).load(pais.bandeira).fit().into(holder.cardImg,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

            // adiciona evento de clique
            holder.itemView.setOnClickListener { onClick(pais) }
        }




    }

