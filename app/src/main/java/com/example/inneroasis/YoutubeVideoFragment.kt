package com.example.inneroasis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

class YoutubeVideoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_youtube_video, container, false)
        val recyclerView = view.findViewById<View>(R.id.natureRecyclerView) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(recyclerView)
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView) {

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()

        // Using the client, perform the HTTP request
        client[
            "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=stress_relief&type=video&key=AIzaSyBeuONBv3vODYD_xGs0o52aGJjzpi8-ehg",
            object : JsonHttpResponseHandler() {

                // Success when HTTP returns 200, reached API
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON)
                {


                    //TODO - Parse JSON into Models
                    val resultsJSON : JSONArray = json.jsonObject.getJSONArray("items")
                    val jsonResponse : String = resultsJSON.toString()

                    val gson = Gson()
                    Log.v("Response", jsonResponse)
                    val modelsType = object : TypeToken<List<YoutubeModel.Results>>() {}.type
                    val models: List<YoutubeModel.Results>? = gson.fromJson(jsonResponse, modelsType)

                    recyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                    recyclerView.adapter = models?.let { getContext()?.let { it1 ->
                        YoutubeRecyclerViewAdapter(it,
                            it1
                        )
                    } }

                    Log.d("InnerOasis", "response successful")


                }

                /*
                 * Failure when HTTP response is any 400
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {

                    t?.message?.let {
                        Log.e("InnerOasis", errorResponse)
                    }
                }
            }]
    }
}