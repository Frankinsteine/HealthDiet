package com.example.healthdiet.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthdiet.models.RecipeDetail
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipeViewModel: ViewModel() {

    var items: MutableLiveData<MutableList<RecipeItem>> = MutableLiveData()

    fun init (context: Context) {
        if (items.value != null)
            return
    }

    private val repo = Repo()
    fun fetchData(): MutableLiveData<MutableList<RecipeItem>> {
        // load data async
        viewModelScope.launch(IO) {
            // for async load need use "postValue", else "setValue"
            items.postValue(repo.getRecipesList())
        }
        return items

    }

    fun fetchEvent(url: String): MutableLiveData<RecipeDetail> {
        val item = MutableLiveData<RecipeDetail>()
        viewModelScope.launch(IO) {
            item.postValue(repo.getRecipe(url))
        }
        return item
    }
}