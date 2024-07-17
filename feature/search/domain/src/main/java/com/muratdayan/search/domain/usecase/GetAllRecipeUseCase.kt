package com.muratdayan.search.domain.usecase

import com.muratdayan.search.domain.model.Recipe
import com.muratdayan.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllRecipeUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(s:String) = flow<NetworkResult<List<Recipe>>> {

    }


}