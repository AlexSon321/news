package com.example.testnews.fragments.loginpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testnews.R
import com.example.testnews.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
        private lateinit var binding: FragmentLoginBinding
        private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        binding.textView2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.button2.setOnClickListener {
            val email = binding.email2.text.toString()
            val password = binding.pass2.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {

                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_loginFragment_to_rootFragment2)
                        } else {
                            Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            else {
                Toast.makeText(requireContext(), "You didn't complete all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){

            findNavController().navigate(R.id.action_loginFragment_to_rootFragment2)
        }
    }

}