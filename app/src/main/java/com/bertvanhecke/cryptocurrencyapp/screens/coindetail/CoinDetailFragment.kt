package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.SharedViewModel
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.models.Coin
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.screens.favorite.FavoriteViewModel
import timber.log.Timber
import java.text.NumberFormat
import java.util.*

class CoinDetailFragment : Fragment() {
    lateinit var binding: FragmentCoinDetailBinding
    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var viewModelFactory: CoinDetailViewModelFactory

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinDetailBinding.inflate(inflater)

        viewModelFactory = CoinDetailViewModelFactory(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailViewModel::class.java)

        val user = UserSingelton.instance().user

        val coin = viewModel.coin

        var filteredCoins = emptyList<Coin>()


        sharedViewModel.favorites.observe(viewLifecycleOwner){
            filteredCoins = sharedViewModel.favorites.value?.filter {
                it.metrics?.id == coin.metrics?.id
            }!!

            if (filteredCoins.isNotEmpty()){
                context?.let { ContextCompat.getColor(it, R.color.yellow_crayola) }
                    ?.let { binding.favoriteCoinButton.setColorFilter(it, PorterDuff.Mode.MULTIPLY) }
            }
        }

        binding.coinId.text = coin.metrics?.id
        binding.coinName.text = coin.metrics?.name
        binding.coinSymbol.text = coin.metrics?.symbol
        binding.coinPriceAbs.text = coin.metrics?.market_data?.price_btc.toString() + coin.metrics?.symbol.toString()
        binding.coinPriceUsd.text = NumberFormat.getCurrencyInstance(Locale("en", "US")).format(coin.metrics?.market_data?.price_usd).toString()
        binding.coinNewsButton.setOnClickListener {
            view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToCoinNewsFragment(coin.metrics?.symbol.toString()))
        }


        binding.favoriteCoinButton.setOnClickListener {
            if(user != null){
                Timber.i(filteredCoins.toString())
                if (filteredCoins.isNotEmpty()){
                    sharedViewModel.removeFavoriteCoin(coin)
                }else {
                    sharedViewModel.addFavoriteCoin(coin)
                }
            } else {
                view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToUserActivity())
            }
        }

        return binding.root
    }

}