package com.puskal.data.model

import java.time.ZonedDateTime

data class PostModel(
    val postId: String,
    val userId: String,
    val createdTime: ZonedDateTime,
    val lastModifiedTime: ZonedDateTime,
    val text: String?,
    val mention: Array<String?>,
    val hashtag: Array<String?>,
    val banned: Boolean = false,
    val visibilitySettings: PostSettings,
    val downloadSettings: PostSettings,
    val commentSettings: PostSettings,
    var noLikes: ULong,
    var noShares: ULong,
) {
    data class PostSettings(
        val group: Group,
        var specified: Array<String?>?,
    ) {
        enum class Group {
            GLOBAL,
            SPECIFIED,
            PRIVATE,
        }
    }
}