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
import android.widget.Toast


class StressEvalFragment : Fragment() {
    private val logTag: String by lazy { getString(R.string.log_tag) }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.showStressButton()
    }

    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.hideStressButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eval_layout, container, false)

        val stressButton = view.findViewById<Button>(R.id.stress_Button)
        val seekBar = view.findViewById<SeekBar>(R.id.stressSeekBar)
        val stressRating = view.findViewById<TextView>(R.id.stress_rating)
        val currentStressRating = view.findViewById<TextView>(R.id.currentStressLevel)



        // Stress seekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                stressRating.text = "Level: $progress"
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


    fun showStressRatingDialog() {

        val seekBar = view?.findViewById<SeekBar>(R.id.stressSeekBar)
        val currentProgress = seekBar?.progress
        val description = currentProgress?.let { getStressLevelDescription(it) }

        AlertDialog.Builder(requireActivity())
            .setTitle("Stress Rating")
            .setMessage("Current Stress Level: $currentProgress\n$description")
            .apply {
                setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }

                if (showOpenFragmentButton(currentProgress)) {
                    setNegativeButton("De-Stress") { dialog, _ ->
                        openBrowseFragment()
                        dialog.dismiss()
                    }
                }
            }
            .show()
    }
    private fun getStressLevelDescription(progress: Int): String {
        return when (progress) {
            in 0..3 -> "Low stress level! Nice!"
            in 4..6 -> "Moderate stress level. Consider practicing mindfulness."
            in 7..10 -> "High stress level. Take a break and relax."
            else -> "Unexpected stress level. Please try again."
        }



    }
    private fun openBrowseFragment() {
        val fragment = BrowseFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.entry_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showOpenFragmentButton(progress: Int?): Boolean {
        // Adjust this condition based on the stress level range where you want to show the button
        return progress != null && progress in 4..10
    }
}



