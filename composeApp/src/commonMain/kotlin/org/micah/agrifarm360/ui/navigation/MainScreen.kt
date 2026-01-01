package org.micah.agrifarm360.ui.navigation

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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.micah.agrifarm360.features.tasks.presentation.TaskViewModel
import org.micah.agrifarm360.features.tasks.presentation.TasksScreen
import org.micah.agrifarm360.ui.screens.dashboard.presentation.DashboardScreen
import org.micah.agrifarm360.ui.screens.expenses.presentation.ExpensesScreen
import org.micah.agrifarm360.ui.screens.reports.presentation.ReportsScreen
import org.micah.agrifarm360.ui.screens.revenue.presentation.RevenueScreen
import org.micah.agrifarm360.ui.screens.workers.presentation.AddWorkerScreen
import org.micah.agrifarm360.ui.screens.workers.presentation.WorkerViewModel
import org.micah.agrifarm360.ui.screens.workers.presentation.WorkersScreen


@Suppress("SuspiciousIndentation")
@Composable
fun MainScreen(){
    val navController= rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    val showBottomNavigation = destination?.let {
        !it.hasRoute<Tasks>()
        !it.hasRoute<AddWorker>()
    } ?: true

    val viewModel : TaskViewModel = koinViewModel<TaskViewModel>()
    val workerViewModel : WorkerViewModel = koinViewModel<WorkerViewModel>()


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

                                    val isSelected = destination?.hasRoute(navigationItem.route::class) == true

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
                                            if (!isSelected) {
                                                navController.navigate(navigationItem.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
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
                    startDestination = Dashboard,
                    navController = navController
                ) {

                    composable<Dashboard> {
                        DashboardScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable<Revenue> {
                        RevenueScreen()
                    }
                    composable<Expenses> {
                        ExpensesScreen()
                    }
                    composable<Reports> {
                        ReportsScreen()
                    }
                    composable<Workers> {
                        WorkersScreen(
                            navController = navController
                        )
                    }
                    composable<AddWorker> {
                        AddWorkerScreen(
                            navController = navController,
                            viewModel = workerViewModel
                        )
                    }
                    composable<Tasks> {
                        TasksScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }


                }

            }
}
