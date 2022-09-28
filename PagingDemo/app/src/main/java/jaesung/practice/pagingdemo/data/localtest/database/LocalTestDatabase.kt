package jaesung.practice.pagingdemo.data.localtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jaesung.practice.pagingdemo.data.localtest.model.Item


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class LocalTestDatabase: RoomDatabase() {
    abstract fun localTestDao(): LocalTestDao
}