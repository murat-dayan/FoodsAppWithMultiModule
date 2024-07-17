package com.muratdayan.search.data.repository

import com.muratdayan.search.data.mappers.toDomain
import com.muratdayan.search.data.remote.SearchApiService
import com.muratdayan.search.domain.model.Recipe
import com.muratdayan.search.domain.model.RecipeDetails
import com.muratdayan.search.domain.repository.SearchRepository

class SearchRepoImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {
    override suspend fun getRecipes(s: String): Result<List<Recipe>> {
        return try {
            val response = searchApiService.getRecipes(s)
            if (response.isSuccessful) {
                response.body()?.meals?.let {
                    Result.success(it.toDomain())
                }?: run { Result.failure(Exception("error occured getrecipes in searchrepoimpl run block")) }
            }else{
                Result.failure(Exception("error occured getrecipes in searchrepoimpl"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }

    }


    override suspend fun getRecipeDetails(id: String): Result<RecipeDetails> {
        return try {
            val response = searchApiService.getRecipeDetails(id)
            if (response.isSuccessful) {
                response.body()?.meals?.let {
                    if (it.isNotEmpty()) {
                        Result.success(it.first().toDomain())
                    }else{
                        Result.failure(Exception("error occured getrecipedetails in searchrepoimpl meals is empty"))
                    }
                }?: run { Result.failure(Exception("error occured getrecipedetails in searchrepoimpl run block")) }
            }else{
                Result.failure(Exception("error occured getrecipedetails in searchrepoimpl"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}