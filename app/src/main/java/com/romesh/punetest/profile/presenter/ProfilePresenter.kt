package com.romesh.punetest.profile.presenter
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.romesh.punetest.utility.Tracer
import com.romesh.punetest.profile.model.ApiResponse
import com.romesh.punetest.network.ApiClient
import com.romesh.punetest.network.ErrorResponse
import retrofit2.HttpException
/*
* This is profile presenter to handle view and model operation
 */
class ProfilePresenter(private val profileView: ProfileView) {
        private val TAG = ProfilePresenter::class.java.simpleName
    fun getUserProfile() {
        profileView.onShowLoadingView()

        val observable = ApiClient.create().profileApiResponse()
        observable!!.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {apiResponse: ApiResponse? ->  apiResponse }
            .subscribe(object : Observer<ApiResponse?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: ApiResponse) {
                    profileView.onSuccess(t)
                }
                override fun onError(e: Throwable) {
                    try {
                        val code =
                            (e as HttpException).response()?.errorBody()!!.string()
                        Tracer.warning(TAG, code)
                        val gson = Gson()
                        val errorResponse: ErrorResponse = gson.fromJson<ErrorResponse>(
                            code,
                            ErrorResponse::class.java
                        )
                        profileView.onFailure(errorResponse)
                    } catch (ex: Exception) {
                        Tracer.warning(TAG, ex.message)
                        val errorResponse =
                            ErrorResponse()
                        errorResponse.error = "Something went wrong on server please try later."
                        profileView.onFailure(errorResponse)
                    }
                }

                override fun onComplete() {}
            })
    }

    }