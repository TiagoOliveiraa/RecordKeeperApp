package com.toliveira.recordkeeper.cycling

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.toliveira.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingRecordBinding
    private lateinit var cyclingPreferences: SharedPreferences
    private val distance: String? by lazy {
        intent.getStringExtra("Distance")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "$distance Record"

        cyclingPreferences = getSharedPreferences("Cycling_Preferences", Context.MODE_PRIVATE)

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonClear.setOnClickListener { clearRecord() }
    }

    private fun saveRecord() {
        cyclingPreferences.edit {
            putString("$distance record", binding.textEditRecord.text.toString())
            putString("$distance date", binding.textEditDate.text.toString())
        }
    }

    private fun clearRecord() {
        cyclingPreferences.edit {
            remove("$distance record")
            remove("$distance date")
        }
        binding.textEditRecord.setText("")
        binding.textEditDate.setText("")
    }


}