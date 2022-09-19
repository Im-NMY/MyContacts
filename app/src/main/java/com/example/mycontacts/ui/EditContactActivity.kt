package com.example.mycontacts.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontacts.MainActivity
import com.example.mycontacts.databinding.ActivityEditContactBinding
import com.example.mycontacts.funExt.focusAndShowKeyboard
import com.example.mycontacts.funExt.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding

    /**
     * Инициализация viewModel упрощенно с помощью KTX
     */
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * requestFocus() автоматически вызывает клавиатуру на заданом EditText
         * или использовать Ext.: focusAndShowKeyboard()
         */
        binding.etName.focusAndShowKeyboard()


        val idContactToEdit: String = intent.getStringExtra("id").toString()

        binding.etName.setText(viewModel.getContactWithId(idContactToEdit)?.name)
        binding.etName.setSelection(binding.etName.length())
        binding.etSurname.setText(viewModel.getContactWithId(idContactToEdit)?.surname)
        binding.etSurname.setSelection(binding.etSurname.length())
        binding.etNumber.setText(viewModel.getContactWithId(idContactToEdit)?.number)
        binding.etNumber.setSelection(binding.etNumber.length())


        binding.btnDelete.setOnClickListener {
            viewModel.deleteContact(idContactToEdit)
            hideKeyboard()
            startActivity(Intent(this@EditContactActivity, MainActivity::class.java))
            finish()

        }

        binding.btnSaveChanges.setOnClickListener {
            viewModel.changeContact(
                name = binding.etName.text.toString(),
                surname = binding.etSurname.text.toString(),
                number = binding.etNumber.text.toString(),
                idContactToEdit
            )
            hideKeyboard()
            startActivity(Intent(this@EditContactActivity, MainActivity::class.java))
            finish()
        }
    }
}