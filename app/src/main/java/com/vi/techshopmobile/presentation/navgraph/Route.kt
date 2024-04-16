package com.vi.techshopmobile.presentation.navgraph

sealed class Route(
    val route: String
) {
    // Comment: Navigation is unique route insist of a list of related screen
    // It would make a route template like this /navigation/screen
    object AppStartNavigation : Route(route = "appStartNavigation")
    object TechShopNavigation : Route(route = "techShopNavigation")
    object AuthenticateNavigation : Route(route = "authenticateNavigation")
    object CheckOutScreenNavigation: Route(route = "checkoutScreenNavigation")
    // Comment: Sub-route
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object HomeScreen: Route(route = "homeScreen")
    object ProductDetailsScreen : Route(route = "productDetailsScreen")
    object SearchScreen: Route(route = "searchScreen")
    object UserSettingScreen: Route(route = "userSettingScreen")
    object CustomerSupportScreen: Route (route = "customerSupportScreen")
    object UserOderScreen: Route(route = "userOderScreen")
    object OderDetailsScreen: Route(route = "orderDetailsScreen")
    object WishListScreen: Route(route = "wishListScreen")
    object SignInScreen: Route(route = "signInScreen")
    object SignUpScreen: Route(route = "signUpScreen")
    object ForgetPasswordScreenEmailScreen: Route(route = "forgetPasswordScreenEmailScreen")
    object ForgetPasswordScreenOTPScreen: Route(route = "forgetPasswordScreenOTPScreen")
    object ForgetPasswordScreenNewPasswordScreen: Route(route = "forgetPasswordScreenNewPasswordScreen")
    object PersonalInfoScreen: Route(route = "personalInfoScreen")
    object PersonalAddressScreen: Route(route = "personalAddressScreen")
    object ChangePasswordScreen: Route(route = "changePasswordScreen")
    object AboutScreen: Route(route = "aboutScreen")
    object ChangeEmailScreen: Route(route = "changeEmailScreen")
    object CartScreen: Route(route = "cartScreen")
    object CheckOutScreen: Route(route = "checkoutScreen")
    object ListInfoScreen: Route(route = "listInfoScreen")
    object PaymentSuccessnScreen: Route(route = "paymentSuccessScreen")
    object PaymentErrorScreen: Route(route = "paymentErrorScreen")
    object AddNewAddressScreen: Route(route = "addNewAddressScreen")
    object DetailAddressScreen: Route(route = "detailAddressScreen")
    object ProductsScreen: Route(route = "productsScreen")
    object FilterProductScreen: Route(route= "filterProductsScreen")
}