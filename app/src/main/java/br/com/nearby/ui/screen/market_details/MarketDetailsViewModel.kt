package br.com.nearby.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nearby.core.network.NearbyRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MarketDetailsUIState()
    )
    val uiState: StateFlow<MarketDetailsUIState> = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailsUIEvent) {
        when (event) {
            is MarketDetailsUIEvent.onFetchCoupon -> fetchCoupon(event.qrCodeContent)
            is MarketDetailsUIEvent.onFetchRules -> fetchRules(event.marketId)
            MarketDetailsUIEvent.onResetCoupon -> resetCoupon()
        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            NearbyRemoteDataSource.patchCoupon(qrCodeContent).fold(
                onSuccess = { result ->
                    _uiState.update { currentUidState ->
                        currentUidState.copy(
                            coupon = result.coupon
                        )
                    }
                },
                onFailure = { _ ->
                    _uiState.update { currentUIState ->
                        currentUIState.copy(
                            coupon = ""
                        )
                    }
                }
            )
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            NearbyRemoteDataSource.getMarketDetails(marketId).fold(
                onSuccess = { marketDetails ->
                    _uiState.update { currentUidState ->
                        currentUidState.copy(
                            rules = marketDetails.rules
                        )
                    }
                },
                onFailure = { _ ->
                    _uiState.update { currentUIState ->
                        currentUIState.copy(
                            rules = emptyList()
                        )
                    }
                }
            )
        }
    }

    private fun resetCoupon() {
        _uiState.update { currentUIState ->
            currentUIState.copy(
                coupon = null
            )
        }
    }
}