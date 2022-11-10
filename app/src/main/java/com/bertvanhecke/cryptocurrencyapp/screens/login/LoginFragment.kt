package com.bertvanhecke.cryptocurrencyapp.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.UserActivity
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)

        binding.toRegisterButton.setOnClickListener { view ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        viewModel = ViewModelProvider(this).get(LoginModel::class.java)

        binding.viewModel = viewModel

        viewModel.loginError.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.username.error = it
            }
        })

        viewModel.errorPassword.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.password.error = it
            }
        })

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                //(activity as UserActivity).setCurrentUser(it)
                UserSingelton.instance().user = it
                requireView().findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
            }
        })

/*        viewModel.navigateBack.observe(viewLifecycleOwner, Observer {
            if (it) {
                (activity as UserActivity).closeActivity()
            }

        })*/

        return binding.root
    }

}