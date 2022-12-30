package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFeedBinding
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import timber.log.Timber

class FeedFragment : Fragment() {
    lateinit var binding: FragmentFeedBinding
    lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater)
        val coinRepository = CoinRepository(CryptoDatabase(requireActivity()))
        val feedViewModelFactory = FeedViewModelFactory(coinRepository)
        feedViewModel = ViewModelProvider(this, feedViewModelFactory).get(FeedViewModel::class.java)


        val adapter = FeedAdapter(CoinListener { coin ->
            feedViewModel.onCoinClicked(coin)
        })

        binding.feedList.adapter = adapter

        feedViewModel.coins.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    // Hide progressbar
                    response.data?.let { coinResponse ->
                        adapter.submitList(coinResponse.data)
                    }
                }
                is Resource.Error -> {
                    //hide progressbar
                    response.message?.let { message ->
                        Timber.i("An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    // show progressbar
                }
            }
        })

        feedViewModel.navigateToCoinDetail.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                this.findNavController().navigate(
                    FeedFragmentDirections
                        .actionFeedFragmentToCoinDetailFragment(coin))
                feedViewModel.onCoinDetailNavigated()
            }
        })



        return binding.root
    }
}