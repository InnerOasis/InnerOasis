package com.example.inneroasis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentPlayActivity : AppCompatActivity() {

    lateinit var favorites: MutableList<VideoEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currentplay_layout)

        val mediaTitle = findViewById<View>(R.id.titleTextView) as TextView?
        val mediaDescription = findViewById<View>(R.id.descriptionTextView) as TextView?
        val youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        val backButton = findViewById<Button>(R.id.backButton)
        val favoriteButton = findViewById<Button>(R.id.favoriteButton)

        favorites = mutableListOf()


        val media = intent.getSerializableExtra("VIDEO_EXTRA") as? YoutubeModel.Results?
        val favoriteMedia = intent.getSerializableExtra("FAV_EXTRA") as? VideoEntity

        // Checks where intent comes from
        if (media != null) {
            mediaTitle?.text = media.snippet?.mediaName
            mediaDescription?.text = media.snippet?.mediaDescription
            youtubePlayerView?.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    media.id?.videoId?.let {
                        youTubePlayer.cueVideo(
                            it,
                            0f
                        )
                    }
                }
            })

            media.id?.videoId?.let { Log.v("Currently Playing", it) }
        } else if (favoriteMedia != null) {                                 // Check if the data is of type VideoEntity
            mediaTitle?.text = favoriteMedia.title
            mediaDescription?.text = favoriteMedia.description
            youtubePlayerView?.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    favoriteMedia.videoId?.let {
                        youTubePlayer.cueVideo(it, 0f)
                    }
                }
            })

            favoriteMedia.videoId?.let { Log.v("Currently Playing", it) }
        }

        lifecycleScope.launch {
            (application as VideoApplication).db.videoDao().getAll().collect { databaseList ->
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

                if (favorites.contains(favoriteMedia) || favorites.any { it.videoId == media?.id?.videoId }) {
                    favoriteButton.isSelected = true
                    favoriteButton.setBackgroundResource(R.drawable.star_filled)
                }

                favoriteButton.setOnClickListener {

                    if (favoriteButton.isSelected) {
                        favoriteButton.isSelected = false
                        favoriteButton.setBackgroundResource(R.drawable.open_star)

                        favorites.remove(
                            VideoEntity(
                                videoId = media?.id?.videoId,
                                title = media?.snippet?.mediaName,
                                description = media?.snippet?.mediaDescription
                            )
                        )
                        lifecycleScope.launch(Dispatchers.IO) {
                            (application as VideoApplication).db.videoDao()
                                .delete(favorites[favorites.lastIndex])
                        }
                        Log.v("Favorites - Delete Favorites", favorites.toString())

                    } else {
                        favoriteButton.isSelected = true
                        favoriteButton.setBackgroundResource(R.drawable.star_filled)
                        favorites.add(
                            VideoEntity(
                                videoId = media?.id?.videoId,
                                title = media?.snippet?.mediaName,
                                description = media?.snippet?.mediaDescription
                            )
                        )
                        lifecycleScope.launch(Dispatchers.IO) {
                            (application as VideoApplication).db.videoDao()
                                .insert(favorites[favorites.lastIndex])
                        }
                        Log.v("Favorites - Add Favorites", favorites.toString())
                    }

                }

            }
            Log.v("Entries Main", favorites.toString())
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
