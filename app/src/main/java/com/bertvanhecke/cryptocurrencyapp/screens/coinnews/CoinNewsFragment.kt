package com.bertvanhecke.cryptocurrencyapp.screens.coinnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentCoinNewsBinding
import com.squareup.picasso.Picasso

class CoinNewsFragment : Fragment() {

    lateinit var binding: FragmentCoinNewsBinding
    private lateinit var viewModel: CoinNewsViewModel
    private lateinit var viewModelFactory: CoinNewsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoinNewsBinding.inflate(inflater)

        viewModelFactory = CoinNewsViewModelFactory(CoinNewsFragmentArgs.fromBundle(requireArguments()).symbol)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinNewsViewModel::class.java)


        viewModel.news.observe(viewLifecycleOwner) {
            Picasso.get().load(it.url).into(binding.newsImage)
            binding.newsTitle.text = it.title
            binding.newsPublished.text = it.published_at
            binding.newsContent.text = it.content
            binding.newsAuthor.text = it.author.name
        }

        return binding.root
    }
}