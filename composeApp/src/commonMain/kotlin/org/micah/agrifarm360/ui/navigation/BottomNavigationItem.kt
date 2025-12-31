package org.micah.agrifarm360.ui.navigation

import agrifarm360.composeapp.generated.resources.Res
import agrifarm360.composeapp.generated.resources.dashboard
import agrifarm360.composeapp.generated.resources.expense
import agrifarm360.composeapp.generated.resources.reports
import agrifarm360.composeapp.generated.resources.revenue
import agrifarm360.composeapp.generated.resources.workers
import org.jetbrains.compose.resources.DrawableResource

enum class BottomNavigation(
    val route: Any,
    val label: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
) {
    Dashboard(
        route = org.micah.agrifarm360.ui.navigation.Dashboard,
        label = "Dashboard",
        selectedIcon = Res.drawable.dashboard,
        unselectedIcon = Res.drawable.dashboard,
    ),
    Revenue(
        route = org.micah.agrifarm360.ui.navigation.Revenue,
        label = "Revenue",
        selectedIcon = Res.drawable.revenue,
        unselectedIcon = Res.drawable.revenue,
    ),
    Expense(
        route = org.micah.agrifarm360.ui.navigation.Expenses,
        label = "Expenses",
        selectedIcon = Res.drawable.expense,
        unselectedIcon = Res.drawable.expense,
    ),
    Reports(
        route = org.micah.agrifarm360.ui.navigation.Reports,
        label = "Reports",
        selectedIcon = Res.drawable.reports,
        unselectedIcon = Res.drawable.reports,
    ),
    Workers(
        route = org.micah.agrifarm360.ui.navigation.Workers,
        label = "Workers",
        selectedIcon = Res.drawable.workers,
        unselectedIcon = Res.drawable.workers,
    )
}
