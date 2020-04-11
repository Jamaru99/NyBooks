package com.example.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.presentation.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.toolbar.*

class BooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolBarMain.title = getString(R.string.books_title)

        val booksViewModel: BooksViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        booksViewModel.bookLiveData.observe(this, Observer {
            it?.let {books ->
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(false)
                    adapter = BooksAdapter(books) {book ->
                        val intent = DetailsActivity.getIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }

        })

        booksViewModel.statusLiveData.observe(this, Observer {
            viewFlipper.displayedChild = it.first
            it.second?.let { errorMessage ->
                textViewError.text = getString(errorMessage)
            }
        })

        booksViewModel.getBooks()
    }
}
