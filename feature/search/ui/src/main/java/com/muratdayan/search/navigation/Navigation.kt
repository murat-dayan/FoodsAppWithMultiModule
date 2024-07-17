package com.muratdayan.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.muratdayan.common.navigation.FeatureApi
import com.muratdayan.common.navigation.NavigationRoute
import com.muratdayan.common.navigation.NavigationSubGraphRoute
import com.muratdayan.search.screens.recipelist.RecipeListScreen
import com.muratdayan.search.screens.recipelist.RecipeListViewModel

interface SearchFeatureApi: FeatureApi

class SearchFeatureImpl: SearchFeatureApi{
    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navHostController: androidx.navigation.NavHostController
    ) {
        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavigationRoute.RecipeList.route
        ){
            composable(route = NavigationRoute.RecipeList.route){
                val recipeListViewModel = hiltViewModel<RecipeListViewModel>()
                RecipeListScreen(
                    recipeListViewModel = recipeListViewModel,
                ){}
            }

            composable(route = NavigationRoute.RecipeDetails.route){

            }

        }
    }

}