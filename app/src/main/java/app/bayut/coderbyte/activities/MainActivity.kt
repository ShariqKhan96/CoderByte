package app.bayut.coderbyte.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.bayut.coderbyte.R
import app.bayut.coderbyte.adapter.BookAdapter
import app.bayut.coderbyte.cache.PrefUtils
import app.bayut.coderbyte.datasource.network.NetworkRepository
import app.bayut.coderbyte.domain.datamodels.Book
import app.bayut.coderbyte.framework.BookSelectionObserver
import app.bayut.coderbyte.framework.DataObserver
import com.google.gson.Gson
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import me.toptas.fancyshowcase.FancyShowCaseQueue
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.listener.OnCompleteListener
import me.toptas.fancyshowcase.listener.OnViewInflateListener


class MainActivity : AppCompatActivity(), DataObserver<List<Book>>, BookSelectionObserver {

    var booksData = mutableListOf<Book>()
    var fancyShowCaseQueue: FancyShowCaseQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIntro()
    }

    private fun showIntro() {
        if (PrefUtils.getString("show_intro", this) == "null") {
            val builder =
                FancyShowCaseView.Builder(this)
            builder.backgroundColor(ContextCompat.getColor(this, R.color.intro_bg))
            builder.closeOnTouch(true)
            builder.roundRectRadius(0)
            builder.disableFocusAnimation()
            builder.focusBorderColor(Color.WHITE)
            builder.customView(R.layout.intro_dashboard_activity, object : OnViewInflateListener {
                override fun onViewInflated(view: View) {
//
                }
            })
            fancyShowCaseQueue = FancyShowCaseQueue()
            fancyShowCaseQueue!!.add(builder.build())
            fancyShowCaseQueue!!.show()
            fancyShowCaseQueue!!.completeListener = object : OnCompleteListener {
                override fun onComplete() {
                    bindAndListen()
                }
            }
            PrefUtils.putString("show_intro", "null", this)
        } else
            bindAndListen()
    }

    private fun bindAndListen() {
        books_rv.adapter = BookAdapter(booksData, this, this)
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            NetworkRepository().getBooks(this, this)
        }
        fetch()
    }

    private fun fetch() {

        //CHECKING LOCAL STORAGE
        val localBooks = Paper.book().read<List<Book>>("books")
        if (localBooks == null)
            NetworkRepository().getBooks(this, this)
        else getDownloadedData(localBooks)
    }

    override fun getDownloadedData(data: List<Book>?) {
        booksData.clear()
        data?.let { booksData.addAll(it) }
        books_rv.adapter?.notifyDataSetChanged()
    }

    override fun onBookSelected(book: Book) {
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//            this@MainActivity,
//            ivProfile as View?,
//            "profile"
//        )
        startActivity(
            Intent(this, DetailActivity::class.java).putExtra(
                "book",
                Gson().toJson(book)
            )
        )
    }
}