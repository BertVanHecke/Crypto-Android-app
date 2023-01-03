package com.bertvanhecke.cryptocurrencyapp.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileModelFactory: ProfileModelFactory
    private lateinit var profileModel: ProfileModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        val user = UserSingelton.instance().user

        profileModelFactory = ProfileModelFactory(user!!)

        profileModel = ViewModelProvider(this, profileModelFactory)[ProfileModel::class.java]

        binding.user = profileModel

        (activity as AppCompatActivity).supportActionBar?.title = user.userName

        binding.lifecycleOwner = this

        binding.logOutButton.setOnClickListener { view ->
            UserSingelton.instance().user = null
            view.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }

        return binding.root
    }
}