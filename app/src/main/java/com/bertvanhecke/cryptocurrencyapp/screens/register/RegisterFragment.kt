package com.bertvanhecke.cryptocurrencyapp.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentRegisterBinding
import com.bertvanhecke.cryptocurrencyapp.screens.login.LoginFragmentDirections


class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        //TODO: Insert Register logic when database is set up.

        binding.toLoginButton.setOnClickListener { view ->
            view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        return binding.root
    }
}