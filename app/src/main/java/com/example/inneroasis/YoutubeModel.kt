package com.example.inneroasis

import com.google.gson.annotations.SerializedName

data class YoutubeModel(
    @SerializedName("items")
    val youtubeItems: List<Results>?
) : java.io.Serializable {
    data class Results(
        @SerializedName("id")
        var id: Id? = null,

        @SerializedName("snippet")
        var snippet: Snippet? = null
    ) : java.io.Serializable

    data class Id(
        @SerializedName("kind")
        var kind: String? = null,

        @SerializedName("videoId")
        var videoId: String? = null,
    ) : java.io.Serializable

    data class Snippet(
        @SerializedName("title")
        var mediaName: String? = null,

        @SerializedName("thumbnails")
        var thumbnails: Thumbnails? = null,

        @SerializedName("description")
        var mediaDescription: String? = null,

        @SerializedName("channelTitle")
        var channelTitle: String? = null,

        @SerializedName("liveBroadcastContent")
        var liveBroadcastContent: String? = null,

        @SerializedName("publishTime")
        var publishTime: String? = null
    ) : java.io.Serializable

    data class Thumbnails(
        @SerializedName("default")
        var defaultThumbnail: Thumbnail? = null,

        @SerializedName("medium")
        var mediumThumbnail: Thumbnail? = null,

        @SerializedName("high")
        var highThumbnail: Thumbnail? = null
    ) : java.io.Serializable

    data class Thumbnail(
        @SerializedName("url")
        var url: String? = null,

        @SerializedName("width")
        var width: Int? = null,

        @SerializedName("height")
        var height: Int? = null
    ) : java.io.Serializable
}
