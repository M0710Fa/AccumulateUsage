package com.example.accumulateusage

sealed class Destination (
    val route: String
    ){

    object MainScreen: Destination(route = "main_screen")
    object PermissionDemandScreen: Destination(route = "pd_screen")
    object DebugScreen: Destination(route = "debug_screen")
}