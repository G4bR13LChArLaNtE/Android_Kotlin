package com.example.appdepizza.listItems

import com.example.appdepizza.R
import com.example.appdepizza.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class Products {

    private val _productList = MutableStateFlow<MutableList<Product>>(mutableListOf())
    private val productListFlow: StateFlow<MutableList<Product>> = _productList

    fun getProducts(): Flow<MutableList<Product>> {
        val productList: MutableList<Product> = mutableListOf(
            Product(
                imgProduct = R.drawable.cheese_pizza,
                name = "Cheese Pizza",
                price = "10.50"
            ),
            Product(
                imgProduct = R.drawable.classic_pizza,
                name = "Classic Pizza",
                price = "23.50"
            ),
            Product(
                imgProduct = R.drawable.mixed_pizza,
                name = "Mixed Pizza",
                price = "25.00"
            ),
            Product(
                imgProduct = R.drawable.salmon_pizza,
                name = "Salmon Pizza",
                price = "14.50"
            )

        )

        _productList.value = productList
        return productListFlow
    }
}