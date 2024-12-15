package br.com.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.nearby.data.model.NearbyMarket
import br.com.nearby.ui.screen.home.HomeScreen
import br.com.nearby.ui.screen.home.HomeViewModel
import br.com.nearby.ui.screen.market_details.MarketDetailsScreen
import br.com.nearby.ui.screen.SplashScreen
import br.com.nearby.ui.screen.WelcomeScreen
import br.com.nearby.ui.route.Home
import br.com.nearby.ui.route.QRCodeScanner
import br.com.nearby.ui.route.Splash
import br.com.nearby.ui.route.Welcome
import br.com.nearby.ui.screen.market_details.MarketDetailsUIEvent
import br.com.nearby.ui.screen.market_details.MarketDetailsViewModel
import br.com.nearby.ui.screen.qrcode.QrCodeScannerScreen
import br.com.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                val navController = rememberNavController()

                val homeViewModel by viewModels<HomeViewModel>()
                val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()
                val marketDetailsUisState by marketDetailsViewModel.uiState.collectAsStateWithLifecycle()

                NavHost(
                    navController = navController,
                    startDestination = Splash,
                ) {
                    composable<Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }
                    composable<Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            onEvent = homeViewModel::onEvent,
                            uiState = homeUiState

                        )
                    }
                    composable<NearbyMarket> { backStack ->
                        val selectedMarket = backStack.toRoute<NearbyMarket>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigateBack = {
                                navController.popBackStack()
                            },
                            uiState = marketDetailsUisState,
                            onEvent = marketDetailsViewModel::onEvent,
                            onNavigateToQRCodeScanner = {
                                navController.navigate(QRCodeScanner)
                            }
                        )
                    }
                    composable<QRCodeScanner> {
                        QrCodeScannerScreen(
                            onCompletedScan = { QRCodeContent ->
                                if (QRCodeContent.isNotEmpty()) {
                                    marketDetailsViewModel.onEvent(
                                        MarketDetailsUIEvent.onFetchCoupon(
                                            QRCodeContent
                                        )
                                    )
                                }
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
