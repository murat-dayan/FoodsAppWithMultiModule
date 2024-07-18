    package com.muratdayan.search.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.muratdayan.common.navigation.FeatureApi
import com.muratdayan.common.navigation.NavigationRoute
import com.muratdayan.common.navigation.NavigationSubGraphRoute
import com.muratdayan.search.screens.recipedetails.RecipeDetailScreen
import com.muratdayan.search.screens.recipedetails.RecipeDetailViewModel
import com.muratdayan.search.screens.recipedetails.RecipeDetailsObject
import com.muratdayan.search.screens.recipelist.RecipeList
import com.muratdayan.search.screens.recipelist.RecipeListScreen
import com.muratdayan.search.screens.recipelist.RecipeListViewModel

interface SearchFeatureApi: FeatureApi

class SearchFeatureImpl: SearchFeatureApi{
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavigationRoute.RecipeList.route
        ){
            composable(route = NavigationRoute.RecipeList.route){
                val recipeListViewModel = hiltViewModel<RecipeListViewModel>()
                RecipeListScreen(
                    recipeListViewModel = recipeListViewModel,
                    navHostController = navHostController
                ){mealId->
                    recipeListViewModel.onEvent(RecipeList.Event.GoToRecipeDetails(mealId))
                }
            }

            composable(route = NavigationRoute.RecipeDetails.route){
                val recipeDetailViewModel = hiltViewModel<RecipeDetailViewModel>()
                val mealId = it.arguments?.getString("id")
                LaunchedEffect(key1 = mealId) {
                    mealId?.let {
                        recipeDetailViewModel.onEvent(RecipeDetailsObject.Event.FetchRecipeDetails(it))
                    }
                }
                RecipeDetailScreen(
                    recipeDetailViewModel = recipeDetailViewModel
                )
            }

        }
    }

}