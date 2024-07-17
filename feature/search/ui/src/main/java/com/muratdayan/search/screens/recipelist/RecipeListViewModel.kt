package com.muratdayan.search.screens.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.utils.NetworkResult
import com.muratdayan.common.utils.UiText
import com.muratdayan.search.domain.model.Recipe
import com.muratdayan.search.domain.usecase.GetAllRecipeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val getAllRecipeUseCase: GetAllRecipeUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(RecipeList.UiState())
    val uiState: StateFlow<RecipeList.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeList.Event){
        when(event){
            is RecipeList.Event.SearchRecipe->{
                search(event.q)
            }
        }
    }

    private fun search(q:String) = getAllRecipeUseCase.invoke(q)
            .onEach {result->
                when(result){
                    is NetworkResult.Loading->{
                        _uiState.update { RecipeList.UiState(isLoading = true) }
                    }
                    is NetworkResult.Success->{
                        _uiState.update { RecipeList.UiState(data = result.data) }
                    }
                    is NetworkResult.Error->{
                        _uiState.update { RecipeList.UiState(error = UiText.RemoteString(result.message.toString())) }
                    }
                }
            }.launchIn(viewModelScope)



}

object RecipeList{
    data class UiState(
        val isLoading:Boolean = false,
        val error:UiText = UiText.Idle,
        val data:List<Recipe>?=null,
    )

    sealed interface Navigation{
        data class GoToRecipeDetails(val id:String):Navigation
    }

    sealed interface Event{
        data class SearchRecipe(val q:String):Event
    }
}