package com.toliveira.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.toliveira.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    private lateinit var runningPreferences: SharedPreferences
    private val distance: String? by lazy {
        intent.getStringExtra("Distance")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "$distance Record"

        runningPreferences = getSharedPreferences("Running_Preferences", Context.MODE_PRIVATE)
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
        binding.textEditRecord.setText(runningPreferences.getString("$distance record", null))
        binding.textEditDate.setText(runningPreferences.getString("$distance date", null))
    }

    private fun clearRecord() {
        runningPreferences.edit {
            remove("$distance record")
            remove("$distance date")
        }

        binding.textEditRecord.setText("")
        binding.textEditDate.setText("")

    }

    private fun saveRecord() {
        runningPreferences.edit {
            putString("$distance record", binding.textEditRecord.text.toString())
            putString("$distance date", binding.textEditDate.text.toString())
        }
    }


}