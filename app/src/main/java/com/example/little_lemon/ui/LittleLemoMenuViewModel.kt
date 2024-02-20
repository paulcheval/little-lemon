package com.example.little_lemon.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.little_lemon.db.MenuDatabase
import com.example.little_lemon.db.MenuItem
import com.example.little_lemon.db.MenuItemRespository
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
import kotlinx.serialization.descriptors.PrimitiveKind

//class LittleLemonMenuViewModel(menuItemRespository: MenuItemRespository): ViewModel() {
class LittleLemonMenuViewModel(application: Application): AndroidViewModel(application) {

    private val respository: MenuItemRespository

    init {
        respository = MenuItemRespository(application)
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val menuItemsNetork = retrieveMenuFromNetwork()
            Log.d("loadData", "Retrieved menuitems ${menuItemsNetork.size}")
            menuItemsNetork.forEach {
                Log.d("loadData", "Adding in ${it.toMenuItem().toString()}")
                respository.addItem(it.toMenuItem())
            }

        }
    }

     fun removeExistingMenuItems(menuItem: MenuItem) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("viewModel", "removing ${menuItem.toString()}")
            respository.deleteItem(menuItem)
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
        Log.d("Items returned", response.toString())


        return response.menu
    }

}