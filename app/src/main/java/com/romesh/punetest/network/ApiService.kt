package com.romesh.punetest.network
import io.reactivex.Observable
import com.romesh.punetest.profile.model.ApiResponse
import com.romesh.punetest.network.ApiEndPoints.API_END
import retrofit2.http.GET
import retrofit2.http.Headers
interface ApiService {
    /**
     * Profile Api Call
     */
    @Headers("Content-Type: application/json")
    @GET(API_END)
    fun profileApiResponse(): Observable<ApiResponse>?

}