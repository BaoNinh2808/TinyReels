package com.puskal.data.model

import com.google.common.primitives.UnsignedLong

data class ProfileModel(
    val profileId: String,
    val avatar: String,
    var following: Array<String?>,
    var followed: Array<String?>,
    var socialMedia: Map<SocialMedia, String?>,
    val stats: ProfileStats,
) {
    data class ProfileStats(
        var noFollowings: ULong = 0UL,
        var noFolloweds: ULong = 0UL,
    )
}

enum class SocialMedia {
    FACEBOOK,
    TIKTOK,
}