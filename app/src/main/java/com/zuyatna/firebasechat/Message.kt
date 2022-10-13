package com.zuyatna.firebasechat

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Message(
    val text: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val timeStamp: Long? = null
) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
