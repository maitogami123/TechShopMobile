package com.vi.techshopmobile.presentation.home.home_navigator

import android.app.Activity
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vi.techshopmobile.LocalToken
import com.vi.techshopmobile.R
import com.vi.techshopmobile.domain.model.Brand
import com.vi.techshopmobile.domain.model.ProductLine
import com.vi.techshopmobile.presentation.about.AboutScreen
import com.vi.techshopmobile.presentation.cart.CartScreen
import com.vi.techshopmobile.presentation.change_email.ChangeEmailScreen
import com.vi.techshopmobile.presentation.change_password.ChangePasswordScreen
import com.vi.techshopmobile.presentation.chatAI.ChatAiScreen
import com.vi.techshopmobile.presentation.checkout.screens.AddNewAddressScreen
import com.vi.techshopmobile.presentation.checkout.screens.DetailAddressScreen
import com.vi.techshopmobile.presentation.filter.FilterScreen
import com.vi.techshopmobile.presentation.home.HomeScreen
import com.vi.techshopmobile.presentation.home.home_navigator.component.BottomNavigationItem
import com.vi.techshopmobile.presentation.home.home_navigator.component.MainTopNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.component.TechShopBottomNavigation
import com.vi.techshopmobile.presentation.home.home_navigator.component.UserInformation
import com.vi.techshopmobile.presentation.home.home_navigator.component.UserTopNavigation
import com.vi.techshopmobile.presentation.navgraph.Route
import com.vi.techshopmobile.presentation.order.UserOrdersScreen
import com.vi.techshopmobile.presentation.order_details.OrderDetailsScreen
import com.vi.techshopmobile.presentation.personal_address.PersonalAddressScreen
import com.vi.techshopmobile.presentation.personal_info.PersonalInfoScreen
import com.vi.techshopmobile.presentation.product_details.ProductDetailsScreen
import com.vi.techshopmobile.presentation.products.ProductsScreen
import com.vi.techshopmobile.presentation.search.SearchResultScreen
import com.vi.techshopmobile.presentation.search.SearchScreen
import com.vi.techshopmobile.presentation.sendEvent
import com.vi.techshopmobile.presentation.user_setting.UserSettingScreen
import com.vi.techshopmobile.presentation.wish_list.WishListScreen
import com.vi.techshopmobile.util.Event
import com.vi.techshopmobile.util.decodeToken
import com.vi.techshopmobile.util.navigateToTap
import android.content.Context as s

val LocalNavController = compositionLocalOf<NavController> {
    error("No LocalNavController provided")
}

val time = 500

@Composable
fun HomeNavigator(navGraphController: NavController) {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_user, text = "User"),
        )
    }
    val viewModel: TechShopNavigatorViewModel = hiltViewModel()
    val isLoggedIn = viewModel.accessToken.isNotBlank();
    val navController = rememberNavController();
    val backStackState = navController.currentBackStackEntryAsState().value;

    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.SearchScreen.route -> 0
            Route.HomeScreen.route -> 1
            Route.UserSettingScreen.route -> 2
            Route.ProductDetailsScreen.route -> 3
            else -> -1
        }
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(topBar = {
            if (selectedItem == 2 && isLoggedIn) {
                // TODO: Fetch user information
                val decodedToken = decodeToken(LocalToken.current)
                UserTopNavigation(
                    userInfo = UserInformation(
                        decodedToken.sub,
                        "placeholder@domain.com",
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/9d58f5a8-7784-442e-80b7-1f17231cb636/dgkoqel-0d33ec31-5bf0-4164-93e0-3d0f58913a91.jpg/v1/fit/w_808,h_808,q_70,strp/aporia_by_cogwurx_dgkoqel-414w-2x.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9ODA4IiwicGF0aCI6IlwvZlwvOWQ1OGY1YTgtNzc4NC00NDJlLTgwYjctMWYxNzIzMWNiNjM2XC9kZ2tvcWVsLTBkMzNlYzMxLTViZjAtNDE2NC05M2UwLTNkMGY1ODkxM2E5MS5qcGciLCJ3aWR0aCI6Ijw9ODA4In1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.5P7jBM1aKS7F0a1p9aI0DuFSH5IMq84T5rACbsgFCSY"
                    )
                )
            } else if (selectedItem == 1) {
                MainTopNavigation(onClick = {
                    if (isLoggedIn) {
                        navController.navigate(Route.CartScreen.route)
                    } else {
                        navGraphController.navigate(Route.AuthenticateNavigation.route)
                    }
                }, isLoggedIn = isLoggedIn)
            }
        }, bottomBar = {
            if (selectedItem != -1 && selectedItem != 3) TechShopBottomNavigation(items = bottomNavigationItem,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTap(navController, Route.SearchScreen.route)
                        1 -> navigateToTap(navController, Route.HomeScreen.route)
                        2 -> {
                            if (isLoggedIn) navigateToTap(
                                navController,
                                Route.UserSettingScreen.route
                            )
                            else {
                                viewModel.sendEvent(Event.Toast("Vui lòng đăng nhập"))
                            }
                        }
                    }
                })
        }) {
            val bottomPadding = it.calculateBottomPadding()
            val topPadding = it.calculateTopPadding()
            NavHost(
                modifier = Modifier.padding(bottom = bottomPadding, top = topPadding),
                navController = navController,
                startDestination = Route.HomeScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(time))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(time))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(time))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(time))
                },
            ) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen(navController)
                }
                composable(
                    route = Route.SearchScreen.route,
                    enterTransition = {
                        fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
                        )
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
                        )
                    },
                    popEnterTransition = {
                        fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
                        )
                    },
                    popExitTransition = {
                        fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
                        )
                    },
                ) {
                    SearchScreen(navController)
                }
                composable(
                    route = Route.UserSettingScreen.route,
                    enterTransition = {
                        fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
                        )
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
                        )
                    },
                    popEnterTransition = {
                        fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
                        )
                    },
                    popExitTransition = {
                        fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
                        )
                    },
                ) {
                    UserSettingScreen(navController)
                }
                composable(route = Route.ProductDetailsScreen.route) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<String?>("productLine")
                        ?.let { productLine ->
                            ProductDetailsScreen(
                                navController = navController,
                                productLine = productLine,
                                isLoggedIn = isLoggedIn
                            ) { navController.navigateUp() }
                        }
                }

                composable(route = Route.CustomerSupportScreen.route) {
                    ChatAiScreen(onNavigateUp = { navController.navigateUp() })
                }

                composable(route = Route.WishListScreen.route) {
                    WishListScreen(onNavigateUp = { navController.navigateUp() }, navController)
                }
                composable(route = Route.PersonalInfoScreen.route) {
                    PersonalInfoScreen(onNavigateUp = { navController.navigateUp() }, navController)
                }
                composable(route = Route.PersonalAddressScreen.route) {
                    PersonalAddressScreen(
                        onNavigateUp = { navController.navigateUp() }, navController
                    )
                }
                composable(route = Route.AddNewAddressScreen.route) {
                    AddNewAddressScreen(navController = navController,
                        onNavigateUp = { navController.navigateUp() })
                }
                composable(
                    route = Route.DetailAddressScreen.route
                ) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<String?>("id")
                        ?.let { id ->
                            DetailAddressScreen(
                                onNavigateUp = { navController.navigateUp() },
                                id = id,
                                navController = navController
                            )
                        }
                }
                composable(route = Route.ChangePasswordScreen.route) {
                    ChangePasswordScreen(
                        onNavigateUp = { navController.navigateUp() },
                    )
                }
                composable(route = Route.ChangeEmailScreen.route) {
                    ChangeEmailScreen(
                        onNavigateUp = { navController.navigateUp() }, navController
                    )
                }
                composable(route = Route.UserOderScreen.route) {
                    UserOrdersScreen(onNavigateUp = { navController.navigateUp() }, navController)
                }
                composable(route = Route.AboutScreen.route) {
                    AboutScreen(onNavigateUp = { navController.navigateUp() })
                }
                composable(route = Route.OderDetailsScreen.route) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<String?>("id")
                        ?.let { id ->
                            OrderDetailsScreen(id,
                                navController = navController,
                                onNavigateUp = { navController.navigateUp() })
                        }
                }
                composable(route = Route.CartScreen.route) {
                    CartScreen(navController = navGraphController) {
                        navController.navigateUp()
                    }
                }
                composable(route = Route.ProductsScreen.route) {
                    val categoryName =
                        navController.previousBackStackEntry?.savedStateHandle?.get<String>("categoryName")
                            ?: ""

                    val brandName =
                        navController.previousBackStackEntry?.savedStateHandle?.get<String>("brandName")
                            ?: ""

                    val productsFilter =
                        navController.previousBackStackEntry?.savedStateHandle?.get<List<ProductLine>>(
                            "productsFilter"
                        ) ?: emptyList()
                    val isFilter =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Boolean>("isFilter")
                            ?: false

                    ProductsScreen(
                        navController = navController,
                        categoryName = categoryName,
                        brandName = brandName,
                        productsFilter = productsFilter,
                        isFilter = isFilter
                    )
                }
                composable(route = Route.FilterProductScreen.route) {
                    val categoryName =
                        navController.previousBackStackEntry?.savedStateHandle?.get<String>("categoryName")
                            ?: ""
                    val brands =
                        navController.previousBackStackEntry?.savedStateHandle?.get<ArrayList<Brand>?>(
                            "brands"
                        ) ?: ArrayList()
                    val products =
                        navController.previousBackStackEntry?.savedStateHandle?.get<List<ProductLine>?>(
                            "products"
                        ) ?: emptyList()
                    val isSearch =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Boolean?>("isSearch")
                            ?: false

                    FilterScreen(
                        navController = navController,
                        category = categoryName,
                        brands = brands,
                        products = products,
                        isSearch = isSearch
                    ) {
                        navController.navigateUp()
                    }
                }
                composable(route = Route.SearchResultScreen.route) {
                    val searchQuery =
                        navController.previousBackStackEntry?.savedStateHandle?.get<String>("searchQuery")
                            ?: ""
                    val productsFilter =
                        navController.previousBackStackEntry?.savedStateHandle?.get<List<ProductLine>>(
                            "productsFilter"
                        ) ?: emptyList()
                    val isFilter =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Boolean>("isFilter")
                            ?: false
                    SearchResultScreen(
                        navController = navController,
                        searchQuery = searchQuery,
                        productsFilter = productsFilter,
                        isFilter = isFilter
                    )

                }
            }
        }
    }
}

fun navigateToDetails(navController: NavController, productLine: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("productLine", productLine)
    navController.navigate(Route.ProductDetailsScreen.route);
}

fun navigateToProducts(
    navController: NavController,
    categoryName: String,
    brandName: String,
    productsFilter: List<ProductLine>,
    isFilter: Boolean
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("categoryName", categoryName)
    navController.currentBackStackEntry?.savedStateHandle?.set("brandName", brandName)
    navController.currentBackStackEntry?.savedStateHandle?.set("productsFilter", productsFilter)
    navController.currentBackStackEntry?.savedStateHandle?.set("isFilter", isFilter)
    navController.navigate(Route.ProductsScreen.route)
}

fun navigateToFilter(
    navController: NavController,
    categoryName: String,
    brands: ArrayList<Brand>,
    products: List<ProductLine>,
    isSearch: Boolean
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("categoryName", categoryName)
    navController.currentBackStackEntry?.savedStateHandle?.set("brands", brands)
    navController.currentBackStackEntry?.savedStateHandle?.set("products", products)
    navController.currentBackStackEntry?.savedStateHandle?.set("isSearch", isSearch)
    navController.navigate(Route.FilterProductScreen.route)
}

fun navigateToSearchResult(
    navController: NavController,
    searchQuery: String,
    productsFilter: List<ProductLine>,
    isFilter: Boolean
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("productsFilter", productsFilter)
    navController.currentBackStackEntry?.savedStateHandle?.set("isFilter", isFilter)
    navController.currentBackStackEntry?.savedStateHandle?.set("searchQuery", searchQuery)
    navController.navigate(Route.SearchResultScreen.route)
}