package com.muratdayan.search.screens.recipedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.common.utils.NetworkResult
import com.muratdayan.common.utils.UiText
import com.muratdayan.search.domain.model.RecipeDetails
import com.muratdayan.search.domain.usecase.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(RecipeDetailsObject.UiState())
    val uiState: StateFlow<RecipeDetailsObject.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeDetailsObject.Event){
        when(event){
            is RecipeDetailsObject.Event.FetchRecipeDetails->{
                recipeDetails(event.id)
            }
        }
    }

    private fun recipeDetails(id: String) = getRecipeDetailsUseCase.invoke(id)
        .onEach {result->
            when(result){
                is NetworkResult.Loading->{
                    _uiState.update { RecipeDetailsObject.UiState(isLoading = true) }
                }
                is NetworkResult.Success->{
                    _uiState.update { RecipeDetailsObject.UiState(data = result.data) }
                    }
                is NetworkResult.Error->{
                    _uiState.update { RecipeDetailsObject.UiState(error = UiText.RemoteString(result.message.toString())) }
                }
            }
        }.launchIn(viewModelScope)

}

object RecipeDetailsObject{
    data class UiState(
        val isLoading:Boolean = false,
        val error: UiText = UiText.Idle,
        val data: RecipeDetails?=null,
    )

    sealed interface Navigation{
        data class GoToRecipeDetails(val id:String):Navigation
    }

    sealed interface Event{
        data class FetchRecipeDetails(val id:String):Event
    }
}