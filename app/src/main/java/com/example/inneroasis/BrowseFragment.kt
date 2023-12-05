package com.example.inneroasis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BrowseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.browse_view, container, false)
        val context = view.context

        val natureRV = view.findViewById<View>(R.id.natureRecyclerView) as RecyclerView
        natureRV.layoutManager = GridLayoutManager(context, 2)
        apiCall(natureRV, searchValue = "nature_sounds")

        val recommendRV = view.findViewById<View>(R.id.recommendRecyclerView) as RecyclerView
        recommendRV.layoutManager = GridLayoutManager(context, 2)
        apiCall(recommendRV, searchValue = "nature_sounds,sensory_videos")

        val sensoryRV = view.findViewById<View>(R.id.sensoryRecyclerView) as RecyclerView
        sensoryRV.layoutManager = GridLayoutManager(context, 2)
        apiCall(sensoryRV, searchValue = "sensory_visuals")

        return view
    }

    private fun apiCall(recyclerView: RecyclerView, searchValue: String) {
        YoutubeApiNetwork.callYoutubeApi(recyclerView, requireContext(), searchValue = searchValue, layout = "horizontal")
    }
}