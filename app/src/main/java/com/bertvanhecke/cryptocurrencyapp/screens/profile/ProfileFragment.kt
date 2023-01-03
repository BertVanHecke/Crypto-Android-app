package com.bertvanhecke.cryptocurrencyapp.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserActivity
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentProfileBinding
import com.bertvanhecke.cryptocurrencyapp.models.User
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileModelFactory: ProfileModelFactory
    private lateinit var profileModel: ProfileModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        val user = UserSingelton.instance().user

        profileModelFactory = ProfileModelFactory(user!!)

        profileModel = ViewModelProvider(this, profileModelFactory).get(ProfileModel::class.java)

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