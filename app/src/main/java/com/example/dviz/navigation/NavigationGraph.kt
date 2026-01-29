package com.example.dviz.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.dviz.presentation.CatalogScreen
import com.example.dviz.presentation.HomeScreen
import com.example.dviz.presentation.PlaceScreen
import com.example.dviz.presentation.SplashScreen
import com.example.dviz.presentation.search.SearchScreen
import com.example.dviz.presentation.user.screens.ForgotPasswordScreen
import com.example.dviz.presentation.user.screens.OTPScreen
import com.example.dviz.presentation.user.screens.ResetPasswordScreen
import com.example.dviz.presentation.user.screens.SignInScreen
import com.example.dviz.presentation.user.screens.SignUpScreen
import com.example.dviz.presentation.user.viewmodel.AuthViewModel
import com.example.dviz.presentation.user.viewmodel.ResetPasswordViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    fun navigateAndPop(destination: Destination, pop: Destination){
        navController.navigate(destination){
            popUpTo(pop){
                inclusive = true}
        }
    }

    fun navigateAndPop(destination: Destination, pop: String){
        navController.navigate(destination){
            popUpTo(pop){ inclusive = true}
        }
    }

    fun navigateAndPop(destination: String, pop: Destination){
        navController.navigate(destination){
            popUpTo(pop){ inclusive = true}
        }
    }

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onDelayFinished = {navigateAndPop(SignIn.route, Splash)}
            )
        }

        navigation(
            startDestination = SignIn.route,
            route = AuthRoute.route
        ) {
            composable(SignIn.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AuthRoute.route)
                }
                val authViewmodel = hiltViewModel<AuthViewModel>(parentEntry)

                SignInScreen(
                    authViewModel = authViewmodel,
                    onSignIn = {navigateAndPop(Home, SignIn.route)},
                    onSignUp = {navController.navigate(SignUp)},
                    onForgotPassword = {navController.navigate(ForgotPassword.route)}
                )
            }

            composable<SignUp> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(AuthRoute.route)
                }
                val authViewmodel = hiltViewModel<AuthViewModel>(parentEntry)

                SignUpScreen(
                    onLogIn = {navigateAndPop(SignIn.route, SignUp)  },
                    onSignUp = {navigateAndPop(Home, SignIn.route)},
                    authViewModel = authViewmodel,
                    onBackClick = {navController.popBackStack()}
                )
            }

            navigation(
                startDestination = ForgotPassword.route,
                route = ResetRoute.route
            ) {
                composable(ForgotPassword.route) { backStackEntry ->

                    val resetParentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(ResetRoute.route)
                    }
                    val resetViewmodel = hiltViewModel<ResetPasswordViewModel>(resetParentEntry)

                    ForgotPasswordScreen(
                        navigateToOtp = { navController.navigate(Otp) },
                        resetPasswordViewModel = resetViewmodel,
                        onBackClick = {navController.popBackStack()}
                    )
                }

                composable<Otp> { backStackEntry ->
                    val resetParentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(ResetRoute.route)
                    }
                    val resetViewmodel = hiltViewModel<ResetPasswordViewModel>(resetParentEntry)

                    OTPScreen(
                        resetPasswordViewModel = resetViewmodel,
                        navigateToResetPassword = {navController.navigate(ResetPassword)},
                        onBack = { navController.popBackStack() }
                    )
                }

                composable<ResetPassword> {backStackEntry ->
                    val authParentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(AuthRoute.route)
                    }
                    val authViewmodel = hiltViewModel<AuthViewModel>(authParentEntry)

                    val resetParentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(ResetRoute.route)
                    }
                    val resetViewmodel = hiltViewModel<ResetPasswordViewModel>(resetParentEntry)

                    ResetPasswordScreen(
                        navigateToMain = {navigateAndPop(Home, ResetPassword)},
                        authViewModel = authViewmodel,
                        resetPasswordViewModel = resetViewmodel
                    )
                }
            }
        }

        composable<Home> {
            HomeScreen(
                onCategory = {navController.navigate(Catalog(it))},
                onSearch = {navController.navigate(Search)}
            )
        }

        composable<Catalog> {
            val args = it.toRoute<Catalog>().catalogId
            CatalogScreen(
                categoryId = args,
                onCard = {navController.navigate(Place)},
                onBack = {},
                navigateToFavorite = {}
            )
        }

        composable<Cart> {

        }

        composable<Order> {

        }

        composable<Place> {
            PlaceScreen()
        }

        composable<Search> {
            SearchScreen(
                onBackClick = {navigateAndPop(Home, Search)}
            )
        }
    }
}