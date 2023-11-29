package com.example.inneroasis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import android.widget.TextView

class StressEvalFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eval_layout, container, false)

        val seekBar = view.findViewById<SeekBar>(R.id.stressSeekBar)
        val stressRating = view.findViewById<TextView>(R.id.stress_rating)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                stressRating.text = progress.toString()


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when the user starts interacting with the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when the user stops interacting with the SeekBar
            }
        })

        return view
    }


}