package com.example.testnews.fragments.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.testnews.R
import com.example.testnews.databinding.FragmentRootBinding
import com.example.testnews.fragments.mainpage.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth


class RootFragment : Fragment() {
        private lateinit var binding: FragmentRootBinding
        private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRootBinding.inflate(inflater,container, false)

        auth = FirebaseAuth.getInstance()

        binding.vPage.adapter = ViewPagerAdapter(context as FragmentActivity)
        binding.tabLayout.tabIconTint = null


        TabLayoutMediator(binding.tabLayout, binding.vPage){
                tab,pos ->
            when(pos){
                0 -> tab.setIcon(R.drawable.ic_baseline_newspaper_24)
                1 -> tab.setIcon(R.drawable.ic_baseline_supervised_user_circle_24)
            }
        }.attach()



        return binding.root
    }

}