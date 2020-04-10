package com.example.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nybooks.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra("EXTRA_TITLE")
        val description = intent.getStringExtra("EXTRA_DESCRIPTION")

        txtTitle.text = title
        txtDescription.text = description
    }

    companion object {
        fun getIntent(context: Context, title: String, description: String): Intent{
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra("EXTRA_TITLE", title)
                putExtra("EXTRA_DESCRIPTION", description)
            }
        }
    }
}
