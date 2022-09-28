package jaesung.practice.pagingdemo.data.localtest.repository

import jaesung.practice.pagingdemo.data.localtest.database.LocalTestDao
import jaesung.practice.pagingdemo.data.localtest.paging.LocalPagingSource

class LocalTestRepository(private val dao: LocalTestDao) {

    fun getPagedList() = LocalPagingSource(dao)

}