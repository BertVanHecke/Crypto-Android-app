package com.bertvanhecke.cryptocurrencyapp.screens.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFeedBinding
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import timber.log.Timber

class FeedFragment : Fragment() {
    lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater)

        Timber.i( "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        val adapter = FeedAdapter(CoinListener { coin ->
            viewModel.onCoinClicked(coin)
        })

        binding.feedList.adapter = adapter

        viewModel.coins.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToCoinDetail.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                this.findNavController().navigate(
                    FeedFragmentDirections
                        .actionFeedFragmentToCoinDetailFragment(coin))
                viewModel.onCoinDetailNavigated()
            }
        })


        return binding.root
    }
}