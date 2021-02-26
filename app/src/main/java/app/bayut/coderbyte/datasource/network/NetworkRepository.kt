package app.bayut.coderbyte.datasource.network

import APIClient
import android.content.Context
import android.widget.Toast
import app.bayut.coderbyte.datasource.business.Check
import app.bayut.coderbyte.domain.datamodels.Book
import app.bayut.coderbyte.domain.datamodels.Root
import app.bayut.coderbyte.framework.DataObserver
import app.bayut.coderbyte.utils.Utils
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository {

    fun getBooks(genericDataInteractor: DataObserver<List<Book>>, context: Context) {

        if (!Check.isOnline(context)) {
            Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show()
            return
        }
        Utils().showLoader(context, "Loading data...")
        APIClient().getClient()?.books()?.enqueue(object : Callback<Root> {
            override fun onFailure(call: Call<Root>, t: Throwable) {
                Utils().dismissLoader()
                //genericDataInteractor.getDownloadedData(null)
            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                Utils().dismissLoader()
                if (response.isSuccessful) {
                    Paper.book().write("books", response.body()?.results)
                    genericDataInteractor.getDownloadedData(response.body()?.results)
                } else {
                    //genericDataInteractor.getDownloadedData(null)
                }
            }

        })
    }
}