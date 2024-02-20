package com.example.little_lemon.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuItemRepository(private val menuItemDao: MenuDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addMenuItem(menuItem: MenuItem) {
        coroutineScope.launch(Dispatchers.IO) {
            menuItemDao.saveMenuItem(menuItem)
        }
    }

    fun deleteMenuItem(menuItem: MenuItem) {
        coroutineScope.launch(Dispatchers.IO) {
            menuItemDao.deleteMenuItem(menuItem)
        }
    }


}