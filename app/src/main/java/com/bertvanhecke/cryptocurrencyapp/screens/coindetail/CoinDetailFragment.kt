package com.bertvanhecke.cryptocurrencyapp.screens.coindetail

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.UserSingelton
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.google.android.material.snackbar.Snackbar


class CoinDetailFragment : Fragment() {
    lateinit var binding: FragmentCoinDetailBinding
    private lateinit var coinDetailViewModel: CoinDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentCoinDetailBinding.inflate(inflater)
        val user = UserSingelton.instance().user
        val coinRepository = CoinRepository(CryptoDatabase(requireNotNull(this.activity)))
        val coinDetailViewModelFactory = CoinDetailViewModelFactory(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin, coinRepository)
        coinDetailViewModel = ViewModelProvider(this, coinDetailViewModelFactory)[CoinDetailViewModel::class.java]

        val coin = coinDetailViewModel.coin

        //Change actionbar title
        (activity as AppCompatActivity).supportActionBar?.title = coin.name

        //Change favorite button to yellow
        context?.let {
            ContextCompat.getColor(it, R.color.yellow_crayola)
        }?.let {
            binding.favoriteCoinButton.setColorFilter(it, PorterDuff.Mode.MULTIPLY)
        }

        binding.coin = coin

        binding.coinNewsButton.setOnClickListener {
            view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToCoinNewsFragment(coin.symbol))
        }

        binding.favoriteCoinButton.setOnClickListener {
            if(user == null){
                view?.findNavController()?.navigate(CoinDetailFragmentDirections.actionCoinDetailFragmentToUserActivity())
            } else {
                coinDetailViewModel.saveCoin(CoinDetailFragmentArgs.fromBundle(requireArguments()).coin, user.id!!)
                view?.let {
                    Snackbar.make(it, "Successfully added coin to favorites.", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

}