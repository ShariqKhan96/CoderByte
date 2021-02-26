package app.bayut.coderbyte.domain.datamodels

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("results") val results: List<Book>
) {
}