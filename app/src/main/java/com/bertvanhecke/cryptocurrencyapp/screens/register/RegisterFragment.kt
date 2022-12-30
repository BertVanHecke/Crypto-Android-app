package com.bertvanhecke.cryptocurrencyapp.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentRegisterBinding
import com.bertvanhecke.cryptocurrencyapp.repository.UserRepository
import com.bertvanhecke.cryptocurrencyapp.screens.login.LoginFragmentDirections


class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        val userRepository = UserRepository(CryptoDatabase(requireNotNull(this.activity)))
        val registerViewModelFactory = RegisterViewModelFactory(userRepository)
        registerViewModel = ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)

        binding.registerViewModel = registerViewModel

        registerViewModel.loginError.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.usernameRegister.error = it
            }
        })

        registerViewModel.errorPassword.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.passwordRegister.error = it
            }
        })

        registerViewModel.errorUserExists.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.usernameRegister.error = it
            }
        })

        registerViewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                UserSingelton.instance().user = it
                requireView().findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToProfileFragment())
            }
        })

        binding.toLoginButton.setOnClickListener { view ->
            view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        return binding.root
    }
}