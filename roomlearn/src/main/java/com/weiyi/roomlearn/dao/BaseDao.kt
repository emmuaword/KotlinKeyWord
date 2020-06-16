package com.weiyi.roomlearn.dao

import androidx.room.*


/**
 * @author TW
 * @date 2020/6/3 14:55
 * @description
 * @mail 2278671454@qq.com
 */
@Dao
abstract class BaseDao<T> {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract suspend fun insertItem(item: T): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract suspend fun insertAll(items: List<T>): List<Long>
//
//    @Update
//    abstract suspend fun update(item: T): Long
//
//    @Update
//    abstract suspend fun updateAll(items: List<T>): List<Long>
//
//    @Delete
//    abstract suspend fun deleteItem(item: T): Long
//
//    @Delete
//    abstract suspend fun deleteAll(items: List<T>): List<Long>
}