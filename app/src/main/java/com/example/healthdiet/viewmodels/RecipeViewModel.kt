package com.example.healthdiet.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthdiet.models.RecipeDetail
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.models.RecipeStep
import com.example.healthdiet.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel() {

    var items: MutableLiveData<MutableList<RecipeItem>> = MutableLiveData()
    var steps: MutableLiveData<MutableList<RecipeStep>> = MutableLiveData()

    fun init (context: Context) {
        if (items.value != null)
            return
        if (steps.value != null)
            return
    }

    private val repo = Repo()
    fun fetchData(): MutableLiveData<MutableList<RecipeItem>> {
        // load data async
        viewModelScope.launch(IO) {
            items.postValue(repo.getRecipesList())
        }
        return items

    }

    fun fetchRecipe(url: String): MutableLiveData<RecipeDetail> {
        val item = MutableLiveData<RecipeDetail>()
        viewModelScope.launch(IO) {
            item.postValue(repo.getRecipe(url))
        }
        Log.d("fetchRecipe", "$item")
        return item
    }

    fun fetchRecipeStep(url: String):MutableLiveData<MutableList<RecipeStep>> {
        viewModelScope.launch(IO) {
            steps.postValue(repo.getStepsList(url))
        }
        Log.d("fetchSteps", "$steps")
        return steps
    }
}