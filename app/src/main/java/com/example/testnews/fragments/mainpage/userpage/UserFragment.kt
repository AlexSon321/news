package com.example.testnews.fragments.mainpage.userpage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.testnews.R
import com.example.testnews.databinding.FragmentUserBinding
import com.example.testnews.model.News
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import java.time.format.DateTimeFormatter


class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)

        dbRef = FirebaseDatabase.getInstance().getReference("News")

        auth = FirebaseAuth.getInstance()

        binding.button3.setOnClickListener {
            val title = binding.titlenew.text.toString()
            val theme = binding.theme.text.toString()
            val text = binding.textnew.text.toString()
            val author = auth.currentUser!!.email

            val date = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd,MM HH:mm:ss")
            val formatedData = date.format(formatter)

            if(inputCheck(title, theme, text, author!!)){
                val key = formatedData
                val news = News(key, title, theme, author, text)
                dbRef.child(key).setValue(news)
                binding.theme.text.clear()
                binding.textnew.text.clear()
                binding.titlenew.text.clear()
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            }


        }

        binding.button4.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_rootFragment2_to_loginFragment)

        }

        return binding.root
    }

    private fun inputCheck(title: String, theme: String, text: String, author: String): Boolean{
        return title.isNotEmpty() && theme.isNotEmpty() && text.isNotEmpty() && author.isNotEmpty()
    }

}