package org.micah.agrifarm360.core.navigation

sealed class Destinations(val route: String) {
    object Dashboard: Destinations("dashboard")
    object Expenses: Destinations("expenses")
    object Onboarding: Destinations("onboarding")
    object Profile: Destinations("profile")

    object Tasks: Destinations("tasks")
    object Reports: Destinations("reports")
    object Revenue: Destinations("revenue")
    object Splash: Destinations("splash")
    object Workers: Destinations("workers")
    object Main: Destinations("main")

}