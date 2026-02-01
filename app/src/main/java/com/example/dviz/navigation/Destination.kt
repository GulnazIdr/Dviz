package com.example.dviz.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object AuthRoute: Destination{
    val route: String = "auth_route"
}

@Serializable
object ResetRoute: Destination{
    val route: String = "reset_route"
}

@Serializable
object Splash: Destination

@Serializable
object SignUp: Destination

@Serializable
object SignIn: Destination{
    val route: String = "sign_in"
}

@Serializable
object Otp: Destination

@Serializable
object ResetPassword: Destination

@Serializable
object ForgotPassword: Destination{
    val route: String = "forgot_password"
}

@Serializable
object Home: Destination

@Serializable
class EventDetails(val eventId: Int): Destination

@Serializable
class Catalog(val catalogId: Int): Destination

@Serializable
object Cart: Destination

@Serializable
object Order: Destination

@Serializable
object Place: Destination

@Serializable
object Search: Destination