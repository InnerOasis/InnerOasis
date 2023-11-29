package com.example.inneroasis

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

object ApiNetwork {
     fun callApi(recyclerView: RecyclerView, context: Context, searchValue: String, layout: String) {

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()

        // Using the client, perform the HTTP request
        client[
            "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q={$searchValue}&type=video&key=AIzaSyCkzvs7bs_5SdAr6Ds_QBz-XMcuoIVUebE",
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

                    if (layout == "horizontal") {
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }

                    recyclerView.adapter = models?.let { context?.let { it1 ->
                        FavoriteViewAdapter(it,
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