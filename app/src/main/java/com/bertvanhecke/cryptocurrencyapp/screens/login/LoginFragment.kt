package com.bertvanhecke.cryptocurrencyapp.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentLoginBinding
import com.bertvanhecke.cryptocurrencyapp.repository.UserRepository

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)

        val userRepository = UserRepository(CryptoDatabase(requireNotNull(this.activity)))
        val loginViewModelFactory = LoginViewModelFactory(userRepository)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]

        binding.toRegisterButton.setOnClickListener { view ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginViewModel = loginViewModel

        loginViewModel.loginError.observe(viewLifecycleOwner) {
            it?.let {
                binding.username.error = it
            }
        }

        loginViewModel.errorPassword.observe(viewLifecycleOwner) {
            it?.let {
                binding.password.error = it
            }
        }

        loginViewModel.errorLogin.observe(viewLifecycleOwner) {
            it?.let {
                binding.username.error = it
            }
        }

        loginViewModel.user.observe(viewLifecycleOwner) {
            it?.let {
                UserSingelton.instance().user = it
                requireView().findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
            }
        }

        return binding.root
    }

}