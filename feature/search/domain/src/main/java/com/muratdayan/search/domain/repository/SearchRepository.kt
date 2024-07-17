package com.muratdayan.search.domain.repository

import com.muratdayan.search.domain.model.Recipe
import com.muratdayan.search.domain.model.RecipeDetails

interface SearchRepository {

    suspend fun getRecipes(s:String): Result<List<Recipe>>

    suspend fun getRecipeDetails(id: String): Result<RecipeDetails>

}