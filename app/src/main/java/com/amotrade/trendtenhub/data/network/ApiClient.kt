package com.amotrade.trendtenhub.data.network

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException

/**
 * Class for retrofit instance creation.
 */
class ApiClient {

    companion object {

        private var retrofit: Retrofit? = null

        private val base_url = "https://run.mocky.io/"

        /**
         * Method to get retrofit instance
         * @return->Retrofit instance
         */
        fun getRetrofitInstance(): Retrofit? {
            checkNotNull(retrofit) { "ApiClient not initialized, use initialize()" }
            return retrofit
        }

        /**
         * Method to instantiate retrofit instance.
         * @param context->Application Context
         */
        fun initialiseRetrofitInstance(context: Context) {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                //creating new file for caching
                val httpCacheDirectory = File(context.cacheDir, "httpCache")
                //creating cache
                val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)

                //Build new Interceptor to cache response
                val httpClient: OkHttpClient = OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(object : Interceptor {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            return try {
                                chain.proceed(chain.request())
                            } catch (e: Exception) {
                                val offlineRequest: Request = chain.request().newBuilder()
                                    .header(
                                        "Cache-Control", "public, only-if-cached," +
                                                "max-stale=" + 60 * 60 * 2
                                    )
                                    .build()
                                chain.proceed(offlineRequest)
                            }
                        }
                    }).addInterceptor(interceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }
        }
    }

}