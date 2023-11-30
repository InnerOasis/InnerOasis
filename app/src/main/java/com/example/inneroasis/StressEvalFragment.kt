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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StressEvalFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eval_layout, container, false)

        val stressButton = view.findViewById<Button>(R.id.stress_Button)
        val seekBar = view.findViewById<SeekBar>(R.id.stressSeekBar)
        val stressRating = view.findViewById<TextView>(R.id.stress_rating)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                stressRating.text = "Stress Level: $progress"


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when the user starts interacting with the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when the user stops interacting with the SeekBar
            }


        })

        stressButton.setOnClickListener{
            Log.v("Stress button was clicked", stressButton.toString())

        }





        return view
    }


}