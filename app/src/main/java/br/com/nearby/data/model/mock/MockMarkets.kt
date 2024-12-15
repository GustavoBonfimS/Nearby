package br.com.nearby.data.model.mock

import br.com.nearby.data.model.NearbyMarket
import br.com.nearby.data.model.NearbyRule

val MockMarkets = listOf(
    NearbyMarket(
        id = "dkaslfjla",
        categoryId = "ldsafjkak",
        name = "Sabor Grill",
        description = "Churrascaria com cortes nobres e buffet variado",
        coupons = 1,
//        rules = listOf(
//            NearbyRule(id = "dlskjfa-sd", description = "Válido até o dia 25/12", marketId = "dkaslfjla"),
//            NearbyRule(id = "dlskjfa-sd", description = "Válido até o dia 25/12", marketId = "dkaslfjla")
//        ),
        latitude = -21.342,
        longitude = -40.342134,
        address = "Av teste",
        phone = "(18) 9999-9999",
        cover = "https://images.unsplash.com/photo-1466220549276-aef9ce186540?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    ),
    NearbyMarket(
        id = "32423-324l12",
        categoryId = "ldsafjkak",
        name = "Mercado 2",
        description = "Mercado 2",
        coupons = 1,
//        rules = emptyList(),
        latitude = -21.342,
        longitude = -40.342134,
        address = "Av teste",
        phone = "(18) 9999-9999",
        cover = "https://images.unsplash.com/photo-1466220549276-aef9ce186540?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    ),
)