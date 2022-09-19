package com.example.mycontacts.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontacts.MainActivity
import com.example.mycontacts.presenter.Presenter
import com.example.mycontacts.data.model.Contact
import com.example.mycontacts.databinding.ActivityAddContactBinding
import com.example.mycontacts.funExt.focusAndShowKeyboard
import com.example.mycontacts.funExt.hideKeyboard
import com.example.mycontacts.presenter.MainAction
import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity(), MainAction {

    private lateinit var binding: ActivityAddContactBinding

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.initAction(this)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         *  автоматически вызывает клавиатуру на заданом EditText
         */
        binding.etName.focusAndShowKeyboard()

        binding.btnSave.setOnClickListener {
            with(binding) {
                presenter.addContact(
                    name = etName.text.toString(),
                    surname = etSurname.text.toString(),
                    number = etNumber.text.toString()
                )
                /**
                 * Убрать клавиатуру по нажатию на кнопку
                 */
                hideKeyboard()
                startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onAddContact(contacts: List<Contact>) {
        Toast.makeText(this, contacts.last().name, Toast.LENGTH_SHORT).show()
    }
}