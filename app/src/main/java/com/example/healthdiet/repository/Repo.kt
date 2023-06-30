package com.example.healthdiet.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.healthdiet.models.RecipeDetail
import org.jsoup.Jsoup
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.models.RecipeStep
import java.io.IOException




class Repo {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: Repo? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    fun getInstance(context: Context): Repo {
        mContext = context
        if (instance == null)
            instance = Repo()

        return instance!!
    }

    fun getRecipesList(): MutableList<RecipeItem> {
        val url = "https://1000.menu/catalog/iz-govyadinj?sostav_arr_remove%5B0%5D=343&sostav_arr_remove%5B1%5D=122&sostav_arr_remove%5B2%5D=124&sostav_arr_remove%5B3%5D=181&sostav_arr_remove%5B4%5D=154&sostav_arr_remove%5B5%5D=129&sostav_arr_remove%5B6%5D=182&sostav_arr_remove%5B7%5D=1566&sostav_arr_remove%5B8%5D=332&es_tt=5"
        val url2 = "https://1000.menu/catalog/kuritsa?ms=1&str=&cat_es_inp%5B%5D=961&sostav_arr_add%5B%5D=36&sostav_arr_add%5B%5D=23&sostav_arr_remove%5B%5D=343&sostav_arr_remove%5B%5D=122&sostav_arr_remove%5B%5D=124&sostav_arr_remove%5B%5D=181&sostav_arr_remove%5B%5D=154&sostav_arr_remove%5B%5D=129&sostav_arr_remove%5B%5D=182&sostav_arr_remove%5B%5D=1566&sostav_arr_remove%5B%5D=332&es_tf=0&es_tt=5&es_cf=0&es_ct=2000"
        val url3 = "https://1000.menu/cooking/search?ms=1&str=&sostav_arr_add%5B%5D=20&sostav_arr_add%5B%5D=23&sostav_arr_remove%5B%5D=343&sostav_arr_remove%5B%5D=122&sostav_arr_remove%5B%5D=124&sostav_arr_remove%5B%5D=181&sostav_arr_remove%5B%5D=154&sostav_arr_remove%5B%5D=129&sostav_arr_remove%5B%5D=182&sostav_arr_remove%5B%5D=1566&sostav_arr_remove%5B%5D=332&es_tf=0&es_tt=5&es_cf=0&es_ct=2000"
        val url4 = "https://1000.menu/cooking/search?ms=1&str=&sostav_arr_add%5B%5D=36&sostav_arr_add%5B%5D=242&sostav_arr_remove%5B%5D=343&sostav_arr_remove%5B%5D=122&sostav_arr_remove%5B%5D=124&sostav_arr_remove%5B%5D=181&sostav_arr_remove%5B%5D=154&sostav_arr_remove%5B%5D=129&sostav_arr_remove%5B%5D=182&sostav_arr_remove%5B%5D=1566&sostav_arr_remove%5B%5D=332&es_tf=0&es_tt=5&es_cf=0&es_ct=2000"
        val url5 = "https://1000.menu/catalog/tvorog?sostav_arr_remove%5B0%5D=343&sostav_arr_remove%5B1%5D=122&sostav_arr_remove%5B2%5D=124&sostav_arr_remove%5B3%5D=181&sostav_arr_remove%5B4%5D=154&sostav_arr_remove%5B5%5D=129&sostav_arr_remove%5B6%5D=182&sostav_arr_remove%5B7%5D=1566&sostav_arr_remove%5B8%5D=332&es_tt=5"
        val listData = mutableListOf<RecipeItem>()
        var recipes = getRecipes(url2)
        listData.addAll(getRecipes(url).slice(1..4))
        listData.addAll(getRecipes(url2))
        listData.addAll(getRecipes(url3).slice(1..5))
        listData.addAll(getRecipes(url4))
        listData.addAll(getRecipes(url5).slice(1..5))
        return listData.shuffled().toMutableList()
    }

    fun getRecipes(url: String): MutableList<RecipeItem> {
        val listData = mutableListOf<RecipeItem>()
        try {
            val doc = Jsoup.connect(url).get()
            val recipes = doc.select("div.cn-item")
            val recipesSize = recipes.size
            for (i in 0 until minOf(10, recipesSize)) {
                val title = recipes.select("a.h5").eq(i).text()
                val time = "Время приготовления: " + recipes.select("div.level-right")
                    .select("span")
                    .eq(i)
                    .text()
                val energyValue ="Калорийность(на 100гр.): " + recipes.select("div.level-left")
                    .select("span")
                    .eq(i)
                    .text()
                val desc = recipes.select("div.preview-text")
                    .eq(i)
                    .text()
                val recipeUrl =
                    "https://1000.menu/" + recipes.select("div.info-preview")
                        .select("a.h5")
                        .eq(i)
                        .attr("href")
                val image = "https:" + recipes.select("div.photo.is-relative")
                    .select("img[src$=.jpg]")
                    .eq(i)
                    .attr("src")
                if(title.isNotEmpty() && time.isNotEmpty() && energyValue.isNotEmpty() && desc.isNotEmpty() && image.isNotEmpty() && recipeUrl.isNotEmpty()) {
                    listData.add(RecipeItem(i, title, time, energyValue, desc, image, recipeUrl))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listData
    }

    fun getRecipe(url: String): RecipeDetail {
        val item = RecipeDetail()
        try {
            val document = Jsoup.connect(url).get()
            val detail = document.select("div#info-box.clrl.fb")
                .select("h1")
                .text()
            val ingredients = document.select("div.ingredient.list-item")
                .select("a.name")
                .text()
            item.detail = detail
            item.ingredients = ingredients
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return item
    }

    fun getStepsList (url: String): MutableList<RecipeStep> {
        val listData = mutableListOf<RecipeStep>()
        try {
            val doc = Jsoup.connect(url).get()
            val steps = doc.select("ol.instructions").select("li")
            val stepsSize = steps.size
            for(i in 0 until stepsSize) {
                val document = Jsoup.connect(url).get()
                val title = document.select("h4").eq(i).text()
                val image = "https:" + document.select("a.step-img.foto_gallery")
                    .eq(i)
                    .attr("href")
                val desc =document.select("p.instruction")
                    .eq(i)
                    .text()
                if(title.isNotEmpty() && image.isNotEmpty() && desc.isNotEmpty()) {
                    listData.add(RecipeStep(title, image, desc))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listData
    }
}