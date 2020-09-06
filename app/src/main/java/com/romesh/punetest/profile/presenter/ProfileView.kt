package com.romesh.punetest.profile.presenter
import com.romesh.punetest.profile.model.ApiResponse
import com.romesh.punetest.network.BaseView
import com.romesh.punetest.network.ErrorResponse
interface ProfileView : BaseView {
    fun onSuccess(apiResponse: ApiResponse?)
    fun onFailure(errorResponse: ErrorResponse?)

}
