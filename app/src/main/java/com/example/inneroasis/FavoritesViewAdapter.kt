package com.example.inneroasis

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FavoritesViewAdapter(private val youtubeVideos: MutableList<VideoEntity>, private val context: Context) :
    RecyclerView.Adapter<FavoritesViewAdapter.YoutubeVideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.media_layout, parent, false)

        return YoutubeVideoViewHolder(contactView)
    }

    override fun getItemCount() = youtubeVideos.size

    override fun onBindViewHolder(holder: YoutubeVideoViewHolder, position: Int) {
        val youtubeVideo = youtubeVideos[position]
        holder.bind(youtubeVideo)
    }

    inner class YoutubeVideoViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private val mediaTitle: TextView? = view.findViewById<View>(R.id.titleTextView) as TextView?
        private val mediaDescription: TextView? = view.findViewById<View>(R.id.descriptionTextView) as TextView?
        val youtubePlayerView: YouTubePlayerView? = view.findViewById(R.id.youtube_player_view)

        init {
            view.setOnClickListener(this)
        }
        fun bind(youtubeVideo: VideoEntity) {
            Log.v("Json", youtubeVideo.toString())
            mediaTitle?.text = youtubeVideo.title
            mediaDescription?.text = youtubeVideo.description
            youtubePlayerView?.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youtubeVideo.videoId?.let {
                        youTubePlayer.cueVideo(
                            it,
                            0f
                        )
                    }
                }
            })
            youtubeVideo.videoId?.let { Log.v("Json", it) }

        }

        override fun onClick(v: View?) {
            val video = youtubeVideos[adapterPosition]
            Log.v("intent", video.toString())
            val intent = Intent(context, CurrentPlayActivity::class.java)
            intent.putExtra("VIDEO_EXTRA", video)
            context.startActivity(intent)

        }
    }
}

