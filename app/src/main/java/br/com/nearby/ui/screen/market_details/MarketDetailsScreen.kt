package br.com.nearby.ui.screen.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nearby.R
import br.com.nearby.data.model.NearbyMarket
import br.com.nearby.data.model.mock.MockMarkets
import br.com.nearby.ui.components.button.NearbyButton
import br.com.nearby.ui.components.market_details.NearbyMarketDetailsCoupons
import br.com.nearby.ui.components.market_details.NearbyMarketDetailsInfos
import br.com.nearby.ui.components.market_details.NearbyMarketDetailsRules
import br.com.nearby.ui.theme.Typography
import coil3.compose.AsyncImage

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    market: NearbyMarket,
    onNavigateBack: () -> Unit = {},
    uiState: MarketDetailsUIState,
    onEvent: (MarketDetailsUIEvent) -> Unit,
    onNavigateToQRCodeScanner: ()-> Unit
) {
    LaunchedEffect(true) {
        onEvent(MarketDetailsUIEvent.onFetchRules(market.id))
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight((0.75f))
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column {
                    Text(text = market.name, style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = market.description, style = Typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(48.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    NearbyMarketDetailsInfos(
                        market = market
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                    if (!uiState.rules.isNullOrEmpty()) {
                        NearbyMarketDetailsRules(
                            rules = uiState.rules
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
                        )
                    }
                    if (!uiState.coupon.isNullOrEmpty()) {
                        NearbyMarketDetailsCoupons(
                            coupons = listOf(uiState.coupon)
                        )
                    }
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQRCodeScanner
                )
            }
        }
        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(
        market = MockMarkets.first(),
        uiState = MarketDetailsUIState(),
        onEvent = {},
        onNavigateToQRCodeScanner = {}
    )
}