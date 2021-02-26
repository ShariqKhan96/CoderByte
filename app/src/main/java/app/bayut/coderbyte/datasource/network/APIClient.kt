import android.util.Log
import app.bayut.coderbyte.cache.PrefUtils
import app.bayut.coderbyte.constants.ConstantManager
import app.bayut.coderbyte.datasource.BooksDataSource
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    fun getClient(): BooksDataSource? {
        if (okHttpClient == null) initOkHttp(
        )
        if (retrofit == null) {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(ConstantManager.Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit?.create(BooksDataSource::class.java)
    }

    private fun initOkHttp() {
        val REQUEST_TIMEOUT = 30
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()


            val request = requestBuilder.build()
            val response = chain.proceed(request)
            val responseCode = response.code()
            Log.e("responseCode", responseCode.toString() + "")
            response
        }
        okHttpClient = httpClient.build()
    }
}