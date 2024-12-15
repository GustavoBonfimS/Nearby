package br.com.nearby.ui.screen.market_details

import br.com.nearby.data.model.NearbyRule

data class MarketDetailsUIState(
    val rules: List<NearbyRule>? = null,
    val coupon: String? = null
)
