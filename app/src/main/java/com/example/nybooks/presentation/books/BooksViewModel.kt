package com.example.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nybooks.data.model.Book

class BooksViewModel : ViewModel() {

    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        val bookList: List<Book> = listOf(
            Book("Pequeno principe", "carne moida"),
            Book("corote", "planta")
        )
        bookLiveData.value = bookList
    }
}