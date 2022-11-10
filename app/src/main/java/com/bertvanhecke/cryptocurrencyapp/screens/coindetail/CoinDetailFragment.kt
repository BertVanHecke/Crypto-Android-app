package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import timber.log.Timber
import java.text.NumberFormat
import java.util.*

class CoinDetailFragment : Fragment() {
    lateinit var binding: FragmentCoinDetailBinding
    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var viewModelFactory: CoinDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinDetailBinding.inflate(inflater)

        viewModelFactory = CoinDetailViewModelFactory(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailViewModel::class.java)

        val coin = viewModel.coin

        binding.coinId.text = coin.metrics.id
        binding.coinName.text = coin.metrics.name
        binding.coinSymbol.text = coin.metrics.symbol
        binding.coinPriceAbs.text = coin.metrics.market_data.price_btc.toString() + coin.metrics.symbol.toString()
        binding.coinPriceUsd.text = NumberFormat.getCurrencyInstance(Locale("en", "US")).format(coin.metrics.market_data.price_usd).toString()
        binding.coinNewsButton.setOnClickListener {
            view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToCoinNewsFragment(coin.metrics.symbol))
        }

        return binding.root
    }

}