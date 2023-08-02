package com.toliveira.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.toliveira.recordkeeper.databinding.ActivityEditRecordBinding

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding

    //private val screenData: ScreenData by lazy {

    //}

    private lateinit var recordPreferences: SharedPreferences
    private val record: String? by lazy {
        intent.getStringExtra("Distance")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "$record Record"

        recordPreferences = getSharedPreferences("Running_Preferences", Context.MODE_PRIVATE)
        displayRecord()
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonClear.setOnClickListener {
            clearRecord()
        }

    }

    private fun displayRecord() {
        binding.textEditRecord.setText(recordPreferences.getString("$record record", null))
        binding.textEditDate.setText(recordPreferences.getString("$record date", null))
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("$record record")
            remove("$record date")
        }

        binding.textEditRecord.setText("")
        binding.textEditDate.setText("")

    }

    private fun saveRecord() {
        recordPreferences.edit {
            putString("$record record", binding.textEditRecord.text.toString())
            putString("$record date", binding.textEditDate.text.toString())
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFiledHint: String
    )


}