package com.example.accumulateusage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.accumulateusage.ui.theme.AccumulateUsageTheme
import com.example.accumulateusage.ui.theme.MainScreen


@Composable
fun AppHost(
    startDestination: String
){
    val navController = rememberNavController()

    AccumulateUsageTheme {
        NavHost(navController = navController, startDestination = startDestination)
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
        composable(route = "main"){
            MainScreen()
        }
        composable(route = "permissionDemand"){
            PermissionDemandScreen(transitionMain = {navController.navigate("main")})
        }
    }
}