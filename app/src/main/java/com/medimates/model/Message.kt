package com.medimates.model

class Message(
    var messageText: String? = null,
    var sender: User? = null,
    var createdAt: Long = 0
)
