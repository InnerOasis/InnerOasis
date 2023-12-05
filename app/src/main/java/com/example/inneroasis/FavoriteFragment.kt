package com.example.inneroasis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.favorite_view, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.favoriteRecyclerView)
        val context = view.context

        val favorites: MutableList<VideoEntity> = mutableListOf()
        recyclerView.layoutManager = GridLayoutManager(context, 2)



        viewLifecycleOwner.lifecycleScope.launch {
            (requireActivity().application as VideoApplication).db.videoDao().getAll().collect { databaseList ->
                favorites.clear()
                databaseList.map { entity ->
                    val video = VideoEntity(
                        id = entity.id,
                        videoId = entity.videoId,
                        title = entity.title,
                        description = entity.description
                    )
                    favorites.add(video)
                }
                Log.v("Entries Main", favorites.toString())

                recyclerView.adapter = favorites.let { context?.let { it1 ->
                    FavoritesViewAdapter(it,
                        it1
                    )
                } }
            }
        }

        return view
    }
}