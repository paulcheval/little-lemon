package com.example.little_lemon.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.little_lemon.db.MenuItemRepository
import com.example.little_lemon.network.MenuItemNetwork
import com.example.little_lemon.network.MenuNetwork
import com.example.little_lemon.utils.toMenuItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LittleLemonMenuViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MenuItemRepository

    init {
        repository = MenuItemRepository(application)
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val menuItemsNetwork = retrieveMenuFromNetwork()
            Log.d("loadData", "Retrieved menuItems ${menuItemsNetwork}")
            menuItemsNetwork.forEach {
                repository.addItem(it.toMenuItem())
            }

        }
    }
    private suspend fun retrieveMenuFromNetwork(): List<MenuItemNetwork> {
        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
        val response: MenuNetwork =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        return response.menu
    }

}