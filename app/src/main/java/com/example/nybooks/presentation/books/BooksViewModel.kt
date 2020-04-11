package com.example.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nybooks.R
import com.example.nybooks.data.ApiService
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val statusLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {

        ApiService.service.getBooks().enqueue(object: Callback<BookBodyResponse> {
            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                statusLiveData.value = Pair(STATUS_FAILURE, R.string.error_unexpected)
            }

            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()
                        response.body()?.let { bookBodyResponse ->
                            for(result in bookBodyResponse.bookResults) {
                                val book = result.bookDetails[0].getBook()
                                books.add(book)
                            }

                            bookLiveData.value = books
                            statusLiveData.value = Pair(STATUS_SUCCESS, null)
                        }
                    }
                    response.code() == 401 -> statusLiveData.value = Pair(STATUS_FAILURE, R.string.error_401)
                    else -> statusLiveData.value = Pair(STATUS_FAILURE, R.string.error_unexpected)
                }

            }

        })
    }

    companion object {
        const val STATUS_SUCCESS = 1
        const val STATUS_FAILURE = 2
    }
}