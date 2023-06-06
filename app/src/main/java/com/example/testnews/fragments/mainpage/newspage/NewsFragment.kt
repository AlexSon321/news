package com.example.testnews.fragments.mainpage.newspage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnews.R
import com.example.testnews.databinding.FragmentNewsBinding
import com.example.testnews.fragments.mainpage.adapters.NewsAdapter
import com.example.testnews.model.News
import com.google.firebase.database.*


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsList: ArrayList<News>
    private lateinit var dbRef: DatabaseReference
    private var adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        newsList = arrayListOf<News>()

        binding.recView.adapter = adapter
        binding.recView.layoutManager = LinearLayoutManager(activity)

        dbRef = FirebaseDatabase.getInstance().getReference("News")

        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                newsList.clear()

                if(p0.exists()){
                    for (empSnap in p0.children){
                        val empData = empSnap.getValue(News::class.java)
                        newsList.add(empData!!)
                    }
                }

                adapter.addElem(newsList)

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }

        })

        return binding.root
    }

}