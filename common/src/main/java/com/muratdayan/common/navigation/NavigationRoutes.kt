package com.muratdayan.common.navigation

sealed class NavigationRoute(val route:String){
    data object RecipeList:NavigationRoute("/recipe_list")
    data object RecipeDetails:NavigationRoute("/recipe_details/{id}"){
        fun sendId(id:String) = "/recipe_details/$id"
    }
}

sealed class NavigationSubGraphRoute(val route: String){
    data object Search:NavigationSubGraphRoute("/search")

}