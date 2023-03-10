package com.appninjas.domain.model

data class Event(val eventId: Int,
                 val eventPictureUrl: String,
                 val eventName: String,
                 val eventDescription: String,
                 val date: String,
                 val category: String,
                 val status: String)
