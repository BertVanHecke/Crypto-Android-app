package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.R
import com.bertvanhecke.cryptocurrencyapp.constants.Constants
import com.bertvanhecke.cryptocurrencyapp.constants.Constants.Companion.BASE_IMAGE
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinNewsBinding
import com.bertvanhecke.cryptocurrencyapp.models.News
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
        coinNewsViewModel = ViewModelProvider(this, coinNewsViewModelFactory)[CoinNewsViewModel::class.java]

        (activity as AppCompatActivity).supportActionBar?.title = "Loading.."


        coinNewsViewModel.coinNews.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    binding.progressBarNews.visibility = View.GONE
                    response.data?.let { newsResponse ->
                        Timber.i(newsResponse.data[0].toString())
                        binding.news = newsResponse.data[0]
                        (activity as AppCompatActivity).supportActionBar?.title = newsResponse.data[0].title
                        setupOptionsMenu(newsResponse.data[0])
                    }
                }
                is Resource.Error -> {
                    binding.progressBarNews.visibility = View.GONE
                    response.message?.let { message ->
                        Timber.i("An error occured: $message")
                        (activity as AppCompatActivity).supportActionBar?.title = "Error, try again."
                    }
                }
                is Resource.Loading -> {
                    binding.progressBarNews.visibility = View.VISIBLE
                    (activity as AppCompatActivity).supportActionBar?.title = "Loading.."
                }
            }
        }


        return binding.root
    }

    private fun setupOptionsMenu(news: News) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.share -> {
                        val shareIntent = coinNewsViewModel.sharePDFNews(news.pdfUrl)
                        startActivity(Intent.createChooser(shareIntent, null))
                        true
                    } else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}