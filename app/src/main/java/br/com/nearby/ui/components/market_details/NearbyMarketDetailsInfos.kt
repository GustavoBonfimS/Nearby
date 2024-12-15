package br.com.nearby.ui.components.market_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nearby.R
import br.com.nearby.data.model.NearbyMarket
import br.com.nearby.data.model.mock.MockMarkets
import br.com.nearby.ui.theme.Gray400
import br.com.nearby.ui.theme.Gray500
import br.com.nearby.ui.theme.Typography

@Composable
fun NearbyMarketDetailsInfos(
    modifier: Modifier = Modifier,
    market: NearbyMarket
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Informações", style = Typography.headlineSmall, color = Gray400)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_ticket),
                    tint = Gray500,
                    contentDescription = "Ícone cupons"
                )
                Text(text = "${market.coupons} cupons disponíveis", style = Typography.labelMedium, color = Gray500)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_map_pin),
                    tint = Gray500,
                    contentDescription = "Endereço"
                )
                Text(text = market.address, style = Typography.labelMedium, color = Gray500)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_phone),
                    tint = Gray500,
                    contentDescription = "Ícone de telefone"
                )
                Text(text = market.phone, style = Typography.labelMedium, color = Gray500)
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsInfosPreview() {
    NearbyMarketDetailsInfos(
        market = MockMarkets.first()
    )
}