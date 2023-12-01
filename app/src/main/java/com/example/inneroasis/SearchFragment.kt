package com.example.inneroasis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_view, container, false)
        val context = view.context

        var searchTerm : String
        val searchInput = view.findViewById<EditText>(R.id.search_input)
        searchTerm = searchInput.text.toString()

        val searchButton = view.findViewById<Button>(R.id.searchButton)

        searchButton.setOnClickListener{
            searchTerm = searchInput.text.toString()
            Log.v("Search Term", searchTerm)
            val natureRV = view.findViewById<View>(R.id.searchRecyclerView) as RecyclerView
            natureRV.layoutManager = GridLayoutManager(context, 2)
            apiCall(natureRV, searchValue = searchTerm)
        }


        return view
    }

    private fun apiCall(recyclerView: RecyclerView, searchValue: String) {
        YoutubeApiNetwork.callYoutubeApi(recyclerView, requireContext(), searchValue = searchValue, layout = "vertical")
    }
}