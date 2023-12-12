package com.puskal.data.model

import java.time.ZonedDateTime

data class VideoModel(
    val imageId: String,
    var postId: Array<String?>,
    val url: String,
    val uploadedTime: ZonedDateTime,
    val fileType: FileType,
    val storageSize: ULong,
    val length: ULong,
    val width: UInt,
    val height: UInt,
    val resolution: UInt,
    var isAvatarOf: Array<String?>,
)

data class ImageModel(
    val imageId: String,
    var postId: Array<String?>,
    val url: String,
    val uploadedTime: ZonedDateTime,
    val fileType: FileType,
    val storageSize: ULong,
    val width: UInt,
    val height: UInt,
    val resolution: UInt,
    var isAvatarOf: Array<String?>,
)

enum class FileType {
    PNG,
    JPG,
}

//data class VideoModel(
//    val videoId: String,
//    val authorDetails: UserModel,
//    val videoStats: VideoStats,
//    val videoLink: String,
//    val description: String,
//    val currentViewerInteraction: ViewerInteraction = ViewerInteraction(),
//    val createdAt: String = randomUploadDate(),
//    val audioModel: AudioModel? = null,
//    val hasTag: List<HasTag> = listOf(),
//) {
//    data class VideoStats(
//        var like: Long,
//        var comment: Long,
//        var share: Long,
//        var favourite: Long,
//        var views: Long = (like.plus(500)..like.plus(100000)).random()
//    ) {
//        var formattedLikeCount: String = ""
//        var formattedCommentCount: String = ""
//        var formattedShareCount: String = ""
//        var formattedFavouriteCount: String = ""
//        var formattedViewsCount: String = ""
//
//        init {
//            formattedLikeCount = like.formattedCount()
//            formattedCommentCount = comment.formattedCount()
//            formattedShareCount = share.formattedCount()
//            formattedFavouriteCount = favourite.formattedCount()
//            formattedViewsCount = views.formattedCount()
//        }
//    }
//
//    data class HasTag(
//        val id: Long,
//        val title: String
//    )
//
//    data class ViewerInteraction(
//        var isLikedByYou: Boolean = false,
//        var isAddedToFavourite: Boolean = false
//    )
//}