package com.romesh.punetest.network
import io.reactivex.schedulers.Schedulers
import com.romesh.punetest.utility.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
/*
 Tis is used to call network operations
 */
class ApiClient {
    companion object {
        private var mRetrofit: Retrofit? = null
        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder().baseUrl(
                    Config.BASE_URL)
                        .client(client).addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(rxAdapter).build()
            }
            return mRetrofit!!.create(
                ApiService::class.java)
        }
    }
}