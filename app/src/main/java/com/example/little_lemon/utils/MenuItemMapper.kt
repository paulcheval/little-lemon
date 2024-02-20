package com.example.little_lemon.utils

import com.example.little_lemon.db.MenuItem
import com.example.little_lemon.network.MenuItemNetwork
import java.text.NumberFormat
import java.util.Locale

fun MenuItemNetwork.toMenuItem() : MenuItem {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val priceAsDecimal = numberFormat.format(price.toDouble())

    return MenuItem(
        id = id,
        title = title,
        description = description,
        price= priceAsDecimal,
        image = image,
        category = category
    )
}