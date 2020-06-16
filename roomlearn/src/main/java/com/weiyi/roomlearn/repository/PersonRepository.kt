package com.weiyi.roomlearn.repository

import com.weiyi.roomlearn.dao.PersonDao
import com.weiyi.roomlearn.entity.Person

/**
 * @author TW
 * @date 2020/6/11 14:58
 * @description
 * @mail 2278671454@qq.com
 */
class PersonRepository private constructor(private val personDao: PersonDao) {

    companion object {
        @Volatile
        private var instance: PersonRepository? = null

        @Synchronized
        fun getInstance(personDao: PersonDao) = instance
                ?: PersonRepository(personDao).also { instance = it }
    }

    fun getPersons() = personDao.getPersons()
//            : LiveData<List<Person>> {
//        return personDao.getPersons()
//    }

//    suspend fun getAllPerson() {
//        return personDao.getAllPerson()
//    }

    suspend fun insertRecord(person: Person) {
        personDao.insertItem(person)
    }

    suspend fun delete(age: Int) {
        personDao.delete(age)
    }
}