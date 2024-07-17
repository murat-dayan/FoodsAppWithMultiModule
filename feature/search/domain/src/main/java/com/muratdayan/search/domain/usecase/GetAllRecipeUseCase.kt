package com.muratdayan.search.domain.usecase

import com.muratdayan.common.utils.NetworkResult
import com.muratdayan.search.domain.model.Recipe
import com.muratdayan.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllRecipeUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(s:String) = flow<NetworkResult<List<Recipe>>> {
        emit(NetworkResult.Loading())
        val response = searchRepository.getRecipes(s)
        if (response.isSuccess){
            emit(NetworkResult.Success(data = response.getOrThrow()))
        }else{
            emit(NetworkResult.Error(response.exceptionOrNull()?.localizedMessage))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}