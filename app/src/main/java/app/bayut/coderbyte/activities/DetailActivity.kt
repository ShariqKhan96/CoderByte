package app.bayut.coderbyte.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import app.bayut.coderbyte.R
import app.bayut.coderbyte.utils.Utils
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    // lateinit var book: Book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bind(
            Gson().fromJson(
                intent.getStringExtra("book"),
                app.bayut.coderbyte.domain.datamodels.Book::class.java
            )
        )
        listen()
    }

    private fun listen() {
        findViewById<FrameLayout>(R.id.toolbar)
            .setOnClickListener {
                onBackPressed()
            }
    }

    private fun bind(book: app.bayut.coderbyte.domain.datamodels.Book?) {
        Glide.with(this)
            .load(book?.image_urls_thumbnails?.get(0))
            .into(book_image)
        book_name.text = book?.name
        published_at.text = Utils.formatDate(book?.created_at)
        book_price.text = book?.price?.split(" ")?.get(1)

    }
}