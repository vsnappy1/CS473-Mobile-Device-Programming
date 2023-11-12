package com.kumar.mdp.data

import com.kumar.mdp.R

val products = listOf(
    Product(
        "iPad",
        "iPad Pro 11-inch",
        400.0,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    ),
    Product(
        "MacBook M3 Pro",
        "12-core CPU\n18-core GPU",
        2500.00,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    ),
    Product(
        "Dell Inspiron",
        "13th Gen Intel® Core™ i7",
        1499.00,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    ),
    Product(
        "MacBook M3 Max",
        "14-core CPU\n30-core GPU",
        3499.00,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    ),
    Product(
        "Logitech Keyboard",
        "Logitech - PRO X\nTKL LIGHTSPEED Wireless",
        199.00,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )
)

val cart = mutableListOf<Product>()
