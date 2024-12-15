package br.com.nearby.core.network

import br.com.nearby.data.model.NearbyCategory
import br.com.nearby.core.network.KtorHttpClient.httpClientAndroid
import br.com.nearby.data.model.Coupon
import br.com.nearby.data.model.MarketDetails
import br.com.nearby.data.model.NearbyMarket
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {
    private const val LOCALHOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"

    private const val BASE_URL = LOCALHOST_EMULATOR_BASE_URL

    suspend fun getCategories(): Result<List<NearbyCategory>> {
        try {
            val categories =
                httpClientAndroid.get("$BASE_URL/categories").body<List<NearbyCategory>>()

            return Result.success(categories)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getMarkets(categoryId: String): Result<List<NearbyMarket>> {
        try {
            val markets: List<NearbyMarket> =
                httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body()

            return Result.success(markets)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> {
        try {
            val market: MarketDetails =
                httpClientAndroid.get("$BASE_URL/markets/$marketId").body()

            return Result.success(market)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> {
        try {
            val coupon = httpClientAndroid.patch("$BASE_URL/coupons/$marketId").body<Coupon>()

            return Result.success(coupon)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}