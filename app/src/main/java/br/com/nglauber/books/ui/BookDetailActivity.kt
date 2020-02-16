package br.com.nglauber.books.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.nglauber.books.R
import br.com.nglauber.books.model.Volume
import br.com.nglauber.books.repository.BookRepository
import br.com.nglauber.books.ui.viewmodel.BookDetailViewModel
import br.com.nglauber.books.ui.viewmodel.BookVmFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    private val viewModel: BookDetailViewModel by lazy {
        ViewModelProvider(
            this, BookVmFactory(
                BookRepository(this)
            )
        ).get(BookDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val volume = intent.getParcelableExtra<Volume?>("book")
        volume?.run {
            txtTitle.text = volumeInfo.title
            txtAuthor.text = volumeInfo.authors?.joinToString()
            txtPages.text = volumeInfo.pageCount.toString()
            txtDescription.text = volumeInfo.description
            Picasso.get()
                .load(volumeInfo.imageLinks?.thumbnail)
                .into(imgCover)

            viewModel.onCreate(volume)
            viewModel.isFavorites.observe(
                this@BookDetailActivity,
                Observer { isFavorite ->
                    if (isFavorite) {
                        fabFavorite.setImageResource(R.drawable.ic_add)
                        fabFavorite.setOnClickListener {
                            viewModel.removeFromFavorites(volume)
                        }
                    } else {
                        fabFavorite.setImageResource(R.drawable.ic_delete)
                        fabFavorite.setOnClickListener {
                            viewModel.saveToFavorites(volume)
                        }
                    }
                }
            )
        }
    }
}