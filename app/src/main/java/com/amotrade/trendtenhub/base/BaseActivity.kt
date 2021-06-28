package com.amotrade.trendtenhub.base

import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import com.amotrade.trendtenhub.R

/**
 * Parent Activity for all the Activities.
 */
abstract class BaseActivity : AppCompatActivity() {

    fun setupToolbar(
        title: String?,
        homeButtonEnable: Boolean,
        refreshEnabled: Boolean = false
    ) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar) ?: return

        if (homeButtonEnable) {
            val backArrow = findViewById<AppCompatImageView>(R.id.backArrowIV)
            backArrow.setOnClickListener { onBackPressed() }
            backArrow.visibility = View.VISIBLE
        }

        val titleTextView = findViewById<TextView>(R.id.toolbar_title_text_view)
        titleTextView.text = title

        if (refreshEnabled) {
            val refresh = findViewById<AppCompatImageView>(R.id.refresh_icon_iv)
            refresh.visibility = View.VISIBLE
        }

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowHomeEnabled(false)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(messageResId: Int) {
        Toast.makeText(
            this, this.getString(messageResId), Toast.LENGTH_SHORT
        ).show()
    }

}