package com.example.tmdbmovie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tmdbmovie.extras.BottomNavigationScreens

@Composable
fun BottomBar(navController: NavHostController){
    val screens  = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.Trending,
        BottomNavigationScreens.Favorite
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(elevation = 8.dp) {
        screens.forEach{screen ->
            if (currentDestination != null) {
                AddItem(
                    screens = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screens: BottomNavigationScreens,
    currentDestination: NavDestination,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
                Text(text = screens.route)
        },
        enabled = true,
        icon = {
            Icon(imageVector = screens.icon, contentDescription = "navigationItem")
        },
        selected = currentDestination.hierarchy.any {
            it.route == screens.route
        },
        onClick = {
            navController.navigate(screens.route)
        })
}

@Composable
fun TMDBSearchBar(
    onSearch: (String) -> Unit
){
    var searchText by remember{ mutableStateOf("") }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(end = 8.dp)
        )
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(it)
            },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search") },
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

    }
}
