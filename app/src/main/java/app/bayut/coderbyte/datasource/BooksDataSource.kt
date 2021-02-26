package app.bayut.coderbyte.datasource

import app.bayut.coderbyte.domain.datamodels.Root
import retrofit2.Call
import retrofit2.http.GET

interface BooksDataSource {
    @GET("default/dynamodb-writer")
    fun books(): Call<Root>
}