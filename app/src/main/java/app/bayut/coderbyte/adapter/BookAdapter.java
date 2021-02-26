package app.bayut.coderbyte.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.bayut.coderbyte.R;
import app.bayut.coderbyte.activities.DetailActivity;
import app.bayut.coderbyte.domain.datamodels.Book;
import app.bayut.coderbyte.framework.BookSelectionObserver;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MYVH> {
    List<Book> books = new ArrayList<>();
    Context context;
    BookSelectionObserver bookSelectionObserver;

    public BookAdapter(List<Book> books, Context context, BookSelectionObserver bookSelectionObserver) {
        this.books = books;
        this.context = context;
        this.bookSelectionObserver = bookSelectionObserver;
    }

    @NonNull
    @Override
    public MYVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MYVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.books_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MYVH holder, int position) {
        holder.setContent(books.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return books
                .size();
    }

    public class MYVH extends RecyclerView.ViewHolder {
        TextView bookName, bookPrice, createdAt;
        ImageView bookImage;

        public MYVH(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.book_name);
            bookPrice = itemView.findViewById(R.id.book_price_crn);
            createdAt = itemView.findViewById(R.id.book_created);
            bookImage = itemView.findViewById(R.id.book_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClicked(view, getAdapterPosition(), bookImage);
                }
            });
        }

        public void setContent(Book book) {

            Glide.with(context)
                    .load(book.getImage_urls_thumbnails().get(0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.image_drawable)
                    .into(bookImage);
            bookName.setText(book.getName());
            bookPrice.setText(book.getPrice().split(" ")[1]);
        }
    }

    private void onClicked(View view, int position, ImageView bookImage) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("book", new Gson().toJson(books.get(position)));
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                        bookImage,   // Starting view
                        "book_image"    // The String
                );
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
