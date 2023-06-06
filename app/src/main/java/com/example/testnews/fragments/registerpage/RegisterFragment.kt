package com.example.testnews.fragments.registerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testnews.R
import com.example.testnews.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
        private lateinit var binding: FragmentRegisterBinding
        private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        binding.button.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val repeat = binding.password.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty() && repeat.isNotEmpty()) {
                if (password == repeat) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_registerFragment_to_rootFragment2)
                        } else {
                            Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Password don't match",Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(requireContext(), "You didn't complete all fields",Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }
}