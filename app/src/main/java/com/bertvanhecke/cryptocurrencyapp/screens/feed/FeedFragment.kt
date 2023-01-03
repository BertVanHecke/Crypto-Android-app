package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFeedBinding
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.utils.Resource
import timber.log.Timber

class FeedFragment : Fragment() {
    lateinit var binding: FragmentFeedBinding
    private lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater)
        val coinRepository = CoinRepository(CryptoDatabase(requireActivity()))
        val feedViewModelFactory = FeedViewModelFactory(coinRepository)
        feedViewModel = ViewModelProvider(this, feedViewModelFactory)[FeedViewModel::class.java]


        val adapter = FeedAdapter(CoinListener { coin ->
            feedViewModel.onCoinClicked(coin)
        })

        binding.feedList.adapter = adapter

        feedViewModel.coins.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let { coinResponse ->
                        adapter.submitList(coinResponse.data)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.message?.let { message ->
                        Timber.i("An error occurred: $message")
                        val builder: AlertDialog.Builder? = activity?.let {
                            AlertDialog.Builder(it)
                        }
                        builder?.setMessage(message)?.setTitle("An error occurred")

                        builder?.create()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        feedViewModel.navigateToCoinDetail.observe(viewLifecycleOwner) { coin ->
            coin?.let {
                this.findNavController().navigate(
                    FeedFragmentDirections
                        .actionFeedFragmentToCoinDetailFragment(coin)
                )
                feedViewModel.onCoinDetailNavigated()
            }
        }



        return binding.root
    }
}