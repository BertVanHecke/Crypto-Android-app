package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.constants.Constants
import com.bertvanhecke.cryptocurrencyapp.constants.Constants.Companion.BASE_IMAGE
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinNewsBinding
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedViewModel
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedViewModelFactory
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import com.squareup.picasso.Picasso
import timber.log.Timber

class CoinNewsFragment : Fragment() {

    lateinit var binding: FragmentCoinNewsBinding
    private lateinit var coinNewsViewModel: CoinNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinNewsBinding.inflate(inflater)
        val coinRepository = CoinRepository(CryptoDatabase(requireActivity()))
        val coinNewsViewModelFactory = CoinNewsViewModelFactory(coinRepository, CoinNewsFragmentArgs.fromBundle(requireArguments()).symbol)
        coinNewsViewModel = ViewModelProvider(this, coinNewsViewModelFactory).get(CoinNewsViewModel::class.java)


        coinNewsViewModel.coinNews.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    binding.progressBarNews.visibility = View.GONE
                    response.data?.let { newsResponse ->
                        binding.news = newsResponse.data.get(0)
                    }
                }
                is Resource.Error -> {
                    binding.progressBarNews.visibility = View.GONE
                    response.message?.let { message ->
                        Timber.i("An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    binding.progressBarNews.visibility = View.VISIBLE
                }
            }
        }


        return binding.root
    }
}