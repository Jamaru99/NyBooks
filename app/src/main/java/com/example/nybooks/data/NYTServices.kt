package com.example.nybooks.data

import com.example.nybooks.data.model.Book
import retrofit2.Call
import retrofit2.http.GET


interface NYTServices {
    @GET("list.json")
    fun listRepos(): Call<List<Book>>
}