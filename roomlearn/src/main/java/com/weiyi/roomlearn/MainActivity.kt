package com.weiyi.roomlearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.weiyi.roomlearn.databinding.ActivityMainBinding
import com.weiyi.roomlearn.entity.Person
import com.weiyi.roomlearn.entity.PersonMessage
import com.weiyi.roomlearn.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initParams()
        initListeners()
    }

    private fun initParams() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModel.MainModelFactory(application)).get(MainViewModel::class.java)
        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
    }

    private fun initListeners() {
        tv_save.setOnClickListener {
            var person = Person()
            person.age = et_age.text.toString().toInt()
            person.idCard = if (et_idcard.text.isNullOrEmpty()) {
                "0"
            } else {
                et_idcard.text.toString()
            }
            person.location = if (et_location.text.isNullOrEmpty()) {
                "无地址"
            } else {
                et_location.text.toString()
            }
            person.name = if (et_name.text.isNullOrEmpty()) {
                "无名氏"
            } else {
                et_name.text.toString()
            }
            person.sex = if ("男" == et_sex.text.toString().trim()) {
                0
            } else {
                1
            }
            var message = PersonMessage(if ("已婚" == et_marriage.text.toString().trim()) {
                "已婚"
            } else {
                "未婚"
            })
            person.message1 = message
            mainViewModel.createRecord(person)
        }
        tv_query.setOnClickListener {
            mainViewModel.notifyPersons()
        }
        tv_del_age.setOnClickListener {
            mainViewModel.deleteAge(if (del_age.text.isNullOrEmpty()) {
                0
            } else {
                del_age.text.toString().toInt()
            })
        }
    }

}
