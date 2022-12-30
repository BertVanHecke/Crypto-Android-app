package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import timber.log.Timber
import java.text.NumberFormat
import java.util.*

class CoinDetailFragment : Fragment() {
    lateinit var binding: FragmentCoinDetailBinding
    private lateinit var coinDetailViewModel: CoinDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinDetailBinding.inflate(inflater)
        val user = UserSingelton.instance().user
        val coinRepository = CoinRepository(CryptoDatabase(requireNotNull(this.activity)))
        val coinDetailViewModelFactory = CoinDetailViewModelFactory(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin, user, coinRepository)
        coinDetailViewModel = ViewModelProvider(this, coinDetailViewModelFactory).get(CoinDetailViewModel::class.java)

        val coin = coinDetailViewModel.coin

        binding.coin = coin

        binding.coinNewsButton.setOnClickListener {
            view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToCoinNewsFragment(coin.symbol))
        }

        binding.favoriteCoinButton.setOnClickListener {
            if(user == null){
                view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToUserActivity())
            } else {
                coinDetailViewModel.saveCoin(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin, user.id!!)
            }
        }

        return binding.root
    }

}