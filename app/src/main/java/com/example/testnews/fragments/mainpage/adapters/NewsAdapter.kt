package com.example.testnews.fragments.mainpage.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnews.R
import com.example.testnews.databinding.NewsItemBinding
import com.example.testnews.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var newList = emptyList<News>()


    class NewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var binding = NewsItemBinding.bind(item)
        fun bind(news: News) = with(binding){
            titleItem.text = news.title
            textItem.text = news.text
            keyItem.text = news.key
            themeItem.text = news.theme
            authItem.text = news.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addElem(news: ArrayList<News>){
        newList = news
        notifyDataSetChanged()
    }

}