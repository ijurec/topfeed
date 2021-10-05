package com.challenge.reddittopfeed.utils

import android.content.Context
import com.challenge.reddittopfeed.R
import java.util.*

class TimeUtil {

    companion object {

        private const val SECOND = 1000

        fun getTimeAgo(context: Context, postDate: String): String {
            val currentDate = Date(System.currentTimeMillis())
            val diff = currentDate.time - Date(postDate.split(".")[0].toLong()).time
            val timeDiffInMinutes = diff / (SECOND * 60)
            context.apply {
                val timeAgo: String = when {
                    timeDiffInMinutes >= (60 * 24) -> {
                        val days = timeDiffInMinutes / 60 / 24
                        days.toString() + if (days == 1L) getString(R.string.day_ago_label) else getString(
                            R.string.days_ago_label
                        )
                    }
                    timeDiffInMinutes >= 60 -> {
                        val hours = timeDiffInMinutes / 60
                        hours.toString() + if (hours == 1L) getString(R.string.hour_ago_label) else getString(
                            R.string.hours_ago_label
                        )
                    }
                    else -> {
                        timeDiffInMinutes.toString() + if (timeDiffInMinutes == 1L) getString(
                            R.string.minute_ago_label
                        ) else getString(
                            R.string.minutes_ago_label
                        )
                    }
                }

                return timeAgo
            }
        }
    }

}