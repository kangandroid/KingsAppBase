package com.king.mobile.downloadlib

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpHelper {

    companion object {
        lateinit var client: OkHttpClient

        fun doRequest(url: String): Response {
            return getOkClient().newCall(Request.Builder().url(url).build()).execute()
        }

        fun doRequestRange(url: String, start: Long, end: Long): Response {
            val request = Request.Builder()
                    .header("RANGE", "bytes=${start}-${end}")
                    .url(url)
                    .build()

            return getOkClient().newCall(request).execute()
        }

        @Synchronized
        private fun getOkClient(): OkHttpClient {
            if (null == client) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY // 设置log
                val builder = CertificatePinner.Builder()
                client = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .certificatePinner(builder.build())
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build()
            }
            return client
        }
    }

}