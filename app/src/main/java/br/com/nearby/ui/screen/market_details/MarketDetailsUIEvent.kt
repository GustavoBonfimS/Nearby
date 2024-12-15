package br.com.nearby.ui.screen.market_details

sealed class MarketDetailsUIEvent {
    data class onFetchRules(val marketId: String): MarketDetailsUIEvent()
    data class onFetchCoupon(val qrCodeContent: String) : MarketDetailsUIEvent()
    data object onResetCoupon : MarketDetailsUIEvent()
}