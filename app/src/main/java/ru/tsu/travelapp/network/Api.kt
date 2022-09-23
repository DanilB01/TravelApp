package ru.tsu.travelapp.network

import retrofit2.http.GET
import retrofit2.http.Header
import ru.tsu.travelapp.network.model.Info

interface Api {

    @GET
    suspend fun getInfo(
        @Header("Accept") acceptParam: String = "application/vnd.github+json",
        @Header("Authorization") token: String = "Bearer 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"
    ): Info
}