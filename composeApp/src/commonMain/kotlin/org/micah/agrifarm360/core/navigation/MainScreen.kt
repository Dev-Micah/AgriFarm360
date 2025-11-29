import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.micah.agrifarm360.core.navigation.BottomNavigation
import org.micah.agrifarm360.core.navigation.Destinations
import org.micah.agrifarm360.features.tasks.data.local.TaskEntity
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel
import org.micah.agrifarm360.features.tasks.presentation.TasksSection
import org.micah.agrifarm360.ui.screens.dashboard.presentation.DashboardScreen
import org.micah.agrifarm360.ui.screens.expenses.presentation.ExpensesScreen
import org.micah.agrifarm360.ui.screens.reports.presentation.ReportsScreen
import org.micah.agrifarm360.ui.screens.revenue.presentation.RevenueScreen
import org.micah.agrifarm360.ui.screens.workers.presentation.WorkersScreen


@Suppress("SuspiciousIndentation")
@Composable
fun MainScreen(){
    val navController= rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

    val showBottomNavigation = currentRoute !in listOf(
        Destinations.Splash.route,
        Destinations.Tasks.route
    )
    val viewModel : TaskViewModel = koinViewModel<TaskViewModel>()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                contentWindowInsets = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal),
                bottomBar = {
                    if (showBottomNavigation) {
                        Column {
                            HorizontalDivider(thickness = 0.8.dp)
                            NavigationBar(
                                tonalElevation = 0.dp,
                                containerColor = MaterialTheme.colorScheme.surface
                            ) {
                                BottomNavigation.entries.forEach { navigationItem ->

                                    val isSelected = currentRoute == navigationItem.route

                                    NavigationBarItem(
                                        selected = isSelected,
                                        icon = {
                                            Icon(
                                                painter = painterResource(if (isSelected) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                                                contentDescription = navigationItem.label
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = navigationItem.label,
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontSize = 10.sp,
                                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                                )
                                            )
                                        },
                                        onClick = {
                                            if (currentRoute != navigationItem.route) {
                                                navController.navigate(navigationItem.route)
                                            }
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                elevation = 0.dp
                                            ),
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )
                                }
                            }

                        }
                    }
                }
            ) {
                NavHost(
                    startDestination = Destinations.Dashboard.route,
                    navController = navController
                ) {

                    composable(Destinations.Dashboard.route) {
                        DashboardScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable(Destinations.Revenue.route) {
                        RevenueScreen()
                    }
                    composable(Destinations.Expenses.route) {
                        ExpensesScreen()
                    }
                    composable(Destinations.Reports.route) {
                        ReportsScreen()
                    }
                    composable(Destinations.Workers.route) {
                        WorkersScreen()
                    }
                    composable(Destinations.Tasks.route) {
                        TasksSection(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }


                }

            }
}