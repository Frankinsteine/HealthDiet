package com.example.healthdiet.repository

import android.content.Context
import android.util.Log
import com.example.healthdiet.models.RecipeDetail
import org.jsoup.Jsoup
import com.example.healthdiet.models.RecipeItem
import com.example.healthdiet.models.RecipeStep
import java.io.IOException

class Repo {

    companion object {
        var instance: Repo? = null
        lateinit var mContext: Context
    }

    fun getInstance(context: Context): Repo {
        mContext = context
        if (instance == null)
            instance = Repo()

        return instance!!
    }

    fun getRecipesList(): MutableList<RecipeItem> {
        val listData = mutableListOf<RecipeItem>()
        try {
            val url = "https://1000.menu/cooking/search?ms=1&str=&sostav_arr_add%5B%5D=22&sostav_arr_add%5B%5D=23&es_tf=0&es_tt=14&es_cf=0&es_ct=2000"
            val doc = Jsoup.connect(url).get()
            val recipes = doc.select("div.cn-item")
            val recipesSize = recipes.size
            for (i in 0 until recipesSize) {
                val title = recipes.select("a.h5")
                    .eq(i)
                    .text()
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
                    .select("img.native")
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
                //.eq(1)//select one or select all in cycle
                .text()
//            val ticketName = document.select("strong.ticket-name")
//                .text()
//            val ticketPrice = document.select("span.ticket-price")
//                .text()
            val ingredients = document.select("div.ingredient.list-item")
                .select("a.name")
                //.eq(1)//select one or select all in cycle
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
        //val url = "https://1000.menu/cooking/40714-kabachki-zapechennye-s-pomidorami-i-syrom-v-duxovke"
        try {
            val doc = Jsoup.connect(url).get()
            val steps = doc.select("ol.instructions").select("li")
            val stepsSize = steps.size
            for(i in 0 until stepsSize) {
                val document = Jsoup.connect(url).get()
                val title = document.select("h4")
                    .eq(i)
                    .text()
                val image = "https:" + document.select("a.step-img.foto_gallery")
                    .eq(i)
                    .attr("href")
                val desc =document.select("p.instruction")
                    .eq(i)
                    .text()
                if(title.isNotEmpty() && image.isNotEmpty() && desc.isNotEmpty()) {
                    Log.d("imgStep", "$image")
                    listData.add(RecipeStep(title, image, desc))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listData
    }

}