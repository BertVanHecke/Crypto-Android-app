package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.bertvanhecke.cryptocurrencyapp.models.User
import timber.log.Timber
import kotlin.reflect.typeOf

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)

        val user = UserSingelton.instance().user


        if(user != null){

            //TODO werkt niet?
            binding.loginView.visibility = View.GONE
            binding.favoriteToLogin.visibility = View.GONE
            binding.favoriteToLoginButton.visibility = View.GONE
            binding.favoriteList.visibility = View.VISIBLE

            viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

            val adapter = FavoriteAdapter(CoinListener { coin ->
                viewModel.onCoinClicked(coin)
            })

            binding.favoriteList.adapter = adapter

            viewModel.favorites.observe(viewLifecycleOwner, Observer {
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
        } else {

            //TODO werkt niet?
            binding.favoriteList.visibility = View.GONE
            binding.loginView.visibility = View.VISIBLE
            binding.favoriteToLogin.visibility = View.VISIBLE
            binding.favoriteToLoginButton.visibility = View.VISIBLE

            binding.favoriteToLoginButton.setOnClickListener {
                findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToUserActivity())
            }
        }

        return binding.root
    }
}