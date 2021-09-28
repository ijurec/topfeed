package com.challenge.reddittopfeed.utils

import java.util.*

class TimeUtil {

    companion object {

        fun getTimeAgo(postDate: String): String {
            val currentDate = Date(System.currentTimeMillis())
            val postDate = Date(postDate.split(".")[0].toLong())
            val diff = currentDate.time - postDate.time
            val timeDiffInMinutes = diff / (1000 * 60)
            val timeAgo: String = when {
                timeDiffInMinutes >= (60 * 24) -> {
                    val days = timeDiffInMinutes / 60 / 24
                    days.toString() + if (days == 1L) " day ago" else " days ago"
                }
                timeDiffInMinutes >= 60 -> {
                    val hours = timeDiffInMinutes / 60
                    hours.toString() + if (hours == 1L) " hour ago" else " hours ago"
                }
                else -> {
                    timeDiffInMinutes.toString() + if (timeDiffInMinutes == 1L) " minute ago" else " minutes ago"
                }
            }
            return timeAgo
        }
    }

}