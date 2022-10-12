package com.example.mycontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.example.gson.JsonModel
import com.example.mycontacts.example.gson.testJson
import com.example.mycontacts.ui.AddContactActivity
import com.example.mycontacts.ui.EditContactActivity
import com.example.mycontacts.ui.MainViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private lateinit var binding: ActivityMainBinding

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            Log.d("Main", "DefaultLifecycleObserver - onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            Log.d("Main", "DefaultLifecycleObserver - onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.d("Main", "DefaultLifecycleObserver - onResume")
        }
    }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Тест Gson для домашнего задания:
         */
        val gson = Gson()
        //Конвертация из строки json в объекты
        val resultObject = gson.fromJson(testJson, JsonModel::class.java )
        Log.e("LOG =>", resultObject.toString())
        //Конвертация из объекты в строки json
        val resultJsonString = gson.toJson(resultObject)
        Log.e("LOG =>", resultJsonString)
        //Конвертация из строки json в объекты
        val resultObject2 = gson.fromJson(resultJsonString,JsonModel::class.java)
        Log.e("LOG =>", resultObject2.toString())
        /**
         * Тест Gson окончен.
         */

        lifecycle.addObserver(defaultLifecycleObserver)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter({ contactToEditIndex ->
            editContact(contactToEditIndex)
        })

        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }

        binding.rvContacts.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(defaultLifecycleObserver)
    }

    private fun editContact(contactToEditIndex: Int) {
        val id = viewModel.getContactId(contactToEditIndex)
        val intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}