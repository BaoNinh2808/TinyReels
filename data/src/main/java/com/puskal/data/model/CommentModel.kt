package com.puskal.data.model

import java.time.ZonedDateTime

data class CommentModel(
    val userId: String,
    val postId: String,
    val createdTime: ZonedDateTime,
    val text: String,
    val mention: Array<String?>?,
    val hashtag: Array<String?>?,
)