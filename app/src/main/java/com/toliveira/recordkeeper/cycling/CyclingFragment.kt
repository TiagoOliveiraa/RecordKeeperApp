package com.toliveira.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toliveira.recordkeeper.running.EditRunningRecordActivity
import com.toliveira.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        displayRecords()
    }

    private fun displayRecords() {
        val cyclingPreferences =
            activity?.getSharedPreferences("Cycling_Preferences", Context.MODE_PRIVATE)

        binding.textView20kmValue.text = cyclingPreferences?.getString("20km record", null)
        binding.textView20kmDate.text = cyclingPreferences?.getString("20km date", null)
        binding.textView50kmValue.text = cyclingPreferences?.getString("50km record", null)
        binding.textView50kmDate.text = cyclingPreferences?.getString("50km date", null)
        binding.textView100kmValue.text = cyclingPreferences?.getString("100km record", null)
        binding.textView100kmDate.text = cyclingPreferences?.getString("100km date", null)
        binding.textViewMaxSpeedValue.text = cyclingPreferences?.getString("Max Speed record", null)
        binding.textViewMaxSpeedDate.text = cyclingPreferences?.getString("Max Speed date", null)
    }

    private fun setupClickListeners() {
        binding.container20km.setOnClickListener { launchCyclingRecordScreen("20km") }
        binding.container50km.setOnClickListener { launchCyclingRecordScreen("50km") }
        binding.container100km.setOnClickListener { launchCyclingRecordScreen("100km") }
        binding.containerMaxSpeed.setOnClickListener { launchCyclingRecordScreen("Max Speed") }
    }

    private fun launchCyclingRecordScreen(distance: String) {
        val intent = Intent(context, EditCyclingRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }


}