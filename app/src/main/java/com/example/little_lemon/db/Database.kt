package com.example.little_lemon.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems(): LiveData<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMenuItem(menuItem: MenuItem)

    @Delete()
    fun deleteMenuItem(menuItem: MenuItem)

}


@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase: RoomDatabase(){
    abstract fun menuItemDao(): MenuDao
}

class MenuItemRepository(context: Context) {
    private val database by lazy {
        Room.databaseBuilder(
            context,
            MenuDatabase::class.java,
            "menu.db"
        ).build()
    }

    fun getAllItems() = database.menuItemDao().getAllMenuItems()

    fun addItem(menuItem: MenuItem) = database.menuItemDao().saveMenuItem(menuItem)

    fun deleteItem(menuItem: MenuItem) = database.menuItemDao().deleteMenuItem(menuItem)


}
