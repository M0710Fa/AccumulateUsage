package com.example.accumulateusage

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.accumulateusage.ui.Debug.DebugScreen
import com.example.accumulateusage.ui.theme.AccumulateUsageTheme
import com.example.accumulateusage.ui.theme.MainScreen


@Composable
fun AppHost(
    startDestination: String
){
    val navController = rememberNavController()

    AccumulateUsageTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            NavHost(navController = navController, startDestination = startDestination)
        }
    }
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Destination.MainScreen.route){
            MainScreen(transitionDebug = {navController.navigate(Destination.DebugScreen.route)})
        }
        composable(route = Destination.PermissionDemandScreen.route){
            PermissionDemandScreen(transitionMain = {navController.navigate(Destination.MainScreen.route)})
        }
        composable(route = Destination.DebugScreen.route){
            DebugScreen()
        }
    }
}