package com.example.inneroasis

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class FavoriteViewAdapter(private val youtubeVideos: List<YoutubeModel.Results>, private val context: Context) :
    RecyclerView.Adapter<FavoriteViewAdapter.YoutubeVideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.media_layout, parent, false)

        return YoutubeVideoViewHolder(contactView)
    }

    override fun getItemCount() = youtubeVideos.size

    override fun onBindViewHolder(holder: YoutubeVideoViewHolder, position: Int) {
        val youtubeVideo = youtubeVideos[position]
        youtubeVideo.snippet?.let { youtubeVideo.id?.let { it1 -> holder.bind(it, it1) } }
    }

    inner class YoutubeVideoViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView),
        View.OnClickListener {

        var item: YoutubeModel? = null
        private val mediaTitle: TextView? = mItemView.findViewById<View>(R.id.titleTextView) as TextView?
        private val mediaDescription: TextView? = mItemView.findViewById<View>(R.id.descriptionTextView) as TextView?
        private val youtubePlayer: YouTubePlayerView = mItemView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        val youtubePlayerView: YouTubePlayerView? = mItemView.findViewById(R.id.youtube_player_view);

        init {
            mItemView.setOnClickListener(this)
        }
        fun bind(youtubeVideo: YoutubeModel.Snippet, youtubeId: YoutubeModel.Id) {
            Log.v("Json", youtubeVideo.toString())
            mediaTitle?.text = youtubeVideo.mediaName
            mediaDescription?.text = youtubeVideo.mediaDescription
            youtubePlayerView?.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youtubeId.videoId?.let {
                        youTubePlayer.cueVideo(
                            it,
                            0f
                        )
                    }
                }
            })
            youtubeId.videoId?.let { Log.v("Json", it) }

        }

        override fun onClick(v: View?) {

        }
    }
}