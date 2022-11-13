package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.MainActivity
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.SharedViewModel
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.bertvanhecke.cryptocurrencyapp.models.User
import timber.log.Timber
import kotlin.reflect.typeOf

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)

        val user = UserSingelton.instance().user
        checkUser(user)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
        val frag = parentFragmentManager.findFragmentById(R.id.frame)
        frag?.let {
            val fragTrans = parentFragmentManager.beginTransaction()
            fragTrans.detach(it)
            fragTrans.attach(it)
            fragTrans.commit()
        }
        val user = UserSingelton.instance().user
        checkUser(user)
    }

    private fun checkUser(user: User?){
        if (user != null) {
            handleUser(binding)
        } else {
            handleNoUser(binding)
        }
    }


    private fun handleUser(binding: FragmentFavoriteBinding){
        //TODO werkt niet?
        binding.loginView.visibility = View.INVISIBLE
        binding.favoriteToLogin.visibility = View.INVISIBLE
        binding.favoriteToLoginButton.visibility = View.INVISIBLE
        binding.favoriteList.visibility = View.VISIBLE


        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        val adapter = FavoriteAdapter(CoinListener { coin ->
            viewModel.onCoinClicked(coin)
        })

        binding.favoriteList.adapter = adapter

        sharedViewModel.favorites.observe(viewLifecycleOwner, Observer {
            Timber.i(it.size.toString())
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToCoinDetail.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                this.findNavController().navigate(
                    FavoriteFragmentDirections
                        .actionFavoriteFragmentToCoinDetailFragment(coin))
                viewModel.onCoinDetailNavigated()
            }
        })
    }

    private fun handleNoUser(binding: FragmentFavoriteBinding) {
        //TODO werkt niet?
        binding.favoriteList.visibility = View.INVISIBLE
        binding.loginView.visibility = View.VISIBLE
        binding.favoriteToLogin.visibility = View.VISIBLE
        binding.favoriteToLoginButton.visibility = View.VISIBLE

        binding.favoriteToLoginButton.setOnClickListener {
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToUserActivity())
        }
    }
}