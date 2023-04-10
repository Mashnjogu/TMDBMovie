package com.example.tmdbmovie.extras

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavHostController
import com.example.tmdbmovie.utils.snackbar.SnackBarManager
import com.example.tmdbmovie.utils.snackbar.SnackBarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class TMDBAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
){

    init {
        coroutineScope.launch {
            snackbarManager.snackBarMessage.filterNotNull().collect{ snackBarMessage ->
                val text = snackBarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun popUp(){
        navController.popBackStack()
    }

    fun navigate(route: String){
        navController.navigate(route){
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUpRoute:String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(popUpRoute){
                inclusive = true
            }
        }
    }

    fun clearAndNavigate(route: String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(0){
                inclusive = true
            }
        }
    }

}