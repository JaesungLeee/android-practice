package jaesung.practice.pagingdemo.util

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import jaesung.practice.pagingdemo.data.localtest.database.LocalTestDatabase
import jaesung.practice.pagingdemo.data.localtest.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ServiceLocator {

    private var dbInstance: LocalTestDatabase? = null

    fun provideDatabase(appContext: Context): LocalTestDatabase {
        return dbInstance ?: synchronized(this) {
            Room.databaseBuilder(
                appContext,
                LocalTestDatabase::class.java,
                "local_paging_db"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    dbInstance?.let { database ->
                        GlobalScope.launch {
                            (0..500).forEach { number ->
                                database.localTestDao().insert(Item(0, "Item $number"))
                            }
                        }
                    }
                }
            }).build().also {
                dbInstance = it
            }
        }
    }

}