package kv.hoa.githubadmin.di.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Contract for information needed on app navigation destination
 */
sealed interface NavDestination {
    val route: String
}

/**
 * @notes for big application better create one module
 * for navigation and define the destination there
 */
sealed class AppNavDestination : NavDestination {
    /**
     * Landing Screen destination
     */
    data object Landing : AppNavDestination() {
        override val route: String = "landing"
    }

    /**
     * User Details destination
     * Use [routeWithArgs] to compose the route instead
     */
    data object UserDetails : AppNavDestination() {
        override val route: String = "userDetails"
        const val LOGIN_NAME_ARG = "loginName"
        val routeWithArgs = "$route/{$LOGIN_NAME_ARG}"
        val arguments = listOf(
            navArgument(LOGIN_NAME_ARG) { type = NavType.StringType }
        )

        fun route(loginName: String): String = "$route/$loginName"
    }
}