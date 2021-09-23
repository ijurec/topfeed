package com.challenge.reddittopfeed.data.remote

import com.challenge.reddittopfeed.data.model.RedditTopFeed

class Response(
    val responseStatus: Status,
    val responseBody: RedditTopFeed? = null,
    val errorMessage: String? = null
) {

    companion object {

        fun success(responseBody: RedditTopFeed): Response = Response(
            responseStatus = Status.SUCCESS,
            responseBody = responseBody,
        )

        fun error(errorMessage: String): Response = Response(
            responseStatus = Status.ERROR,
            errorMessage = errorMessage

        )

        fun inProgress(): Response = Response(
            responseStatus = Status.IN_PROGRESS
        )

    }

    enum class Status {
        SUCCESS, ERROR, IN_PROGRESS
    }
}