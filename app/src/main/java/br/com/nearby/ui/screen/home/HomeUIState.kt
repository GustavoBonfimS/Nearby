package br.com.nearby.ui.screen.home

import br.com.nearby.data.model.NearbyCategory
import br.com.nearby.data.model.NearbyMarket
import com.google.android.gms.maps.model.LatLng

data class HomeUIState(
    val categories: List<NearbyCategory>? = null,
    val markets: List<NearbyMarket>? = null,
    val marketLocations: List<LatLng>? = null
)
