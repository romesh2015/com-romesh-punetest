package com.romesh.punetest.network
import com.google.gson.annotations.SerializedName
/*
 * Error handling model define
 */
class ErrorResponse {
    @SerializedName("error")
    var error: String? = null
    var statusCode = 0

}