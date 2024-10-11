package kv.hoa.githubadmin.di.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoa.kv.githubadmin.landing.MainScreen
import hoa.kv.githubadmin.userdetails.UserDetailsScreen

/**
 * Main navigation graph for the Art Gallery Viewer app.
 * @param startDestination The starting destination of the NavHost.
 * @param navController The NavController to be used by the NavHost.
 */
@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    startDestination: AppNavDestination = AppNavDestination.Landing,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination.route,
        navController = navController,
    ) {
        composable(AppNavDestination.Landing.route) {
            MainScreen(
                snackbarHostState = snackbarHostState,
                onUserItemClicked = { userLoginName ->
                    navController.navigate(AppNavDestination.UserDetails.route(userLoginName))
                }
            )
        }
        composable(
            route = AppNavDestination.UserDetails.routeWithArgs,
            arguments = AppNavDestination.UserDetails.arguments
        ) { navBackStackEntry ->
            val loginName = navBackStackEntry.arguments?.getString(
                AppNavDestination.UserDetails.LOGIN_NAME_ARG
            )
            if (loginName.isNullOrEmpty()) {
                throw Exception("Cannot navigate to UserDetails Screen cause ${AppNavDestination.UserDetails.LOGIN_NAME_ARG} is null or empty")
            }
            UserDetailsScreen(
                userLoginName = loginName,
                snackbarHostState = snackbarHostState
            ) {
                navController.navigateUp()
            }
        }
    }
}