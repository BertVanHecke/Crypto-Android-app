package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bertvanhecke.cryptocurrencyapp.constants.Constants
import com.bertvanhecke.cryptocurrencyapp.models.News
import com.squareup.picasso.Picasso

@BindingAdapter("image")
fun ImageView.loadImageFromUrl(item: News?) {
    item?.let {
        if (it.previewImage == ""){
            Picasso.get().load(Constants.BASE_IMAGE).into(this)
        }else {
            Picasso.get().load(it.previewImage).into(this)
        }
    }
}

@BindingAdapter("title")
fun TextView.setNewsTitle(item: News?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("published")
fun TextView.setNewsPublished(item: News?) {
    item?.let {
        text = item.published_at
    }
}

@BindingAdapter("content")
fun TextView.setNewsContent(item: News?) {
    item?.let {
        text = item.content
    }
}

@BindingAdapter("author")
fun TextView.setNewsAuthor(item: News?) {
    item?.let {
        text = item.author.name
    }
}