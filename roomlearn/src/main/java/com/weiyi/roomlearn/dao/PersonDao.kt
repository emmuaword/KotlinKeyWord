package com.weiyi.roomlearn.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weiyi.roomlearn.entity.Person


/**
 * @author TW
 * @date 2020/6/3 14:53
 * @description
 * @mail 2278671454@qq.com
 */
@Dao
abstract class PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItem(item: Person): Long

    @Query("SELECT * FROM person")
    abstract fun getPersons(): LiveData<List<Person>>

//    @Query("SELECT * FROM $PERSON_TABLE")
//    suspend fun getAllPerson():Cursor

    @Query("SELECT * FROM person WHERE sex=:sex ")
    abstract fun getMale(sex: Int): LiveData<List<Person>>

    //特殊用法:Query做查询用于删除
    @Query("DELETE FROM person WHERE age=:age")
    abstract fun delete(age: Int): Unit

}