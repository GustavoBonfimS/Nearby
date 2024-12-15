package br.com.nearby.ui.screen.home

sealed class HomeUIEvent {
    data object onFetchCategories : HomeUIEvent()
    data class onFetchMarkets(val categoryId: String) : HomeUIEvent()
}