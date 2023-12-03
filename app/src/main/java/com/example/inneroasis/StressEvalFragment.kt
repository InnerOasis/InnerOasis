package com.example.inneroasis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.app.AlertDialog


class StressEvalFragment : Fragment() {
    private val logTag: String by lazy { getString(R.string.log_tag) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eval_layout, container, false)

        val stressButton = view.findViewById<Button>(R.id.stress_Button)
        val seekBar = view.findViewById<SeekBar>(R.id.stressSeekBar)
        val stressRating = view.findViewById<TextView>(R.id.stress_rating)
        val currentStressRating = view.findViewById<TextView>(R.id.currentStressLevel)

        //function for showing stress level dialog
        fun showStressRatingDialog() {
            val currentProgress = seekBar.progress
            val message = "Current Stress Level: $currentProgress"

            AlertDialog.Builder(requireContext())
                .setTitle("Stress Rating")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Stress seekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                stressRating.text = "Stress Level: $progress"
                updateStressLevelDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when the user starts interacting with the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when the user stops interacting with the SeekBar
            }
        })

        stressButton.setOnClickListener {
            Log.d(logTag, "Button was clicked. Stress Level: ${seekBar.progress}")
            currentStressRating.text = "Current Stress Level: ${seekBar.progress}"
            showStressRatingDialog()
        }

        return view
    }

    private fun updateStressLevelDescription(progress: Int) {
        when (progress) {
            in 1..3 -> {
                // Handle the case for low stress level
            }
            in 4..6 -> {
                // Handle the case for moderate stress level
            }
            in 7..10 -> {
                // Handle the case for high stress level
            }
            else -> {
                // Handle unexpected values
            }
        }

    }
}



