package com.weiyi.roomlearn.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.weiyi.roomlearn.database.AppDataBase
import com.weiyi.roomlearn.entity.Person
import com.weiyi.roomlearn.repository.PersonRepository
import kotlinx.coroutines.launch

/**
 * @author TW
 * @date 2020/6/11 11:38
 * @description
 * @mail 2278671454@qq.com
 */
class MainViewModel constructor(application: Application) : AndroidViewModel(application) {
    val appDataBase: AppDataBase = AppDataBase.getInstance(application)
    val personRepository: PersonRepository = PersonRepository.getInstance(appDataBase.getPersonDao())
    var persons: LiveData<List<Person>> = personRepository.getPersons()


    fun createRecord(person: Person) {
        viewModelScope.launch {
            personRepository.insertRecord(person)
        }
    }

    fun notifyPersons() {
        viewModelScope.launch {
//            persons.value = personRepository.getAllPerson()
        }
    }

    fun deleteAge(age: Int) {
        viewModelScope.launch {
            personRepository.delete(age)
        }

    }

    class MainModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }

}