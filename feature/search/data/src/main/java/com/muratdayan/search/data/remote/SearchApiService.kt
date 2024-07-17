package com.muratdayan.search.data.remote

import com.muratdayan.search.data.model.RecipeDetailResponse
import com.muratdayan.search.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    // https://www.themealdb.com/api/json/v1/1/search.php?s=Chicken

    @GET("api/json/v1/1/search.php")
    suspend fun getRecipes(
        @Query("s") s:String
    ): Response<RecipeResponse>

    // https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetails(
        @Query("i") i:String
    ): Response<RecipeDetailResponse>
}