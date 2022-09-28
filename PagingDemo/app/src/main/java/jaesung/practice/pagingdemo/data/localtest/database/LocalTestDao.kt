package jaesung.practice.pagingdemo.data.localtest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jaesung.practice.pagingdemo.data.localtest.model.Item

@Dao
interface LocalTestDao {

    @Query("SELECT * FROM items ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPaginationList(limit: Int, offset: Int): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item): Long
}