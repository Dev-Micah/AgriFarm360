package org.micah.agrifarm360.ui.navigation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.dashboard
import agrifarm360.composeapp.generated.resources.expense
import agrifarm360.composeapp.generated.resources.revenue
import agrifarm360.composeapp.generated.resources.workers
import org.jetbrains.compose.resources.DrawableResource

enum class BottomNavigation(
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val route: String

) {
    Dashboard(
        label = "Dashboard",
        selectedIcon = Res.drawable.dashboard,
        unselectedIcon = Res.drawable.dashboard,
        route = Destinations.Dashboard.route
    ),
    Revenue(
        label = "Revenue",
        selectedIcon = Res.drawable.revenue,
        unselectedIcon = Res.drawable.revenue,
        route = Destinations.Revenue.route
    ),
    Expense(
        label = "Expenses",
        selectedIcon = Res.drawable.expense,
        unselectedIcon = Res.drawable.expense,
        route = Destinations.Expenses.route
    ),
//    Reports(
//        label = "Reports",
//        selectedIcon = Res.drawable.reports,
//        unselectedIcon = Res.drawable.reports,
//        route = Destinations.Reports.route
//    ),
    Workers(
        label = "Workers",
        selectedIcon = Res.drawable.workers,
        unselectedIcon = Res.drawable.workers,
        route = Destinations.Workers.route
    )
}