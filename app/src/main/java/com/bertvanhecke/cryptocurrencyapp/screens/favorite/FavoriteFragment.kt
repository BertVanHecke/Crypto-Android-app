package com.bertvanhecke.cryptocurrencyapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bertvanhecke.cryptocurrencyapp.*
import com.bertvanhecke.cryptocurrencyapp.database.CryptoDatabase
import com.bertvanhecke.cryptocurrencyapp.databinding.FragmentFavoriteBinding
import com.bertvanhecke.cryptocurrencyapp.models.User
import com.bertvanhecke.cryptocurrencyapp.repository.CoinRepository
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedAdapter
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedViewModel
import com.bertvanhecke.cryptocurrencyapp.screens.feed.FeedViewModelFactory
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater)
        val user = UserSingelton.instance().user

        val coinRepository = CoinRepository(CryptoDatabase(requireActivity()))
        val favoriteViewModelFactory = FavoriteViewModelFactory(coinRepository)

        checkUser(user, favoriteViewModelFactory)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
        val frag = parentFragmentManager.findFragmentById(R.id.frame)
        frag?.let {
            val fragTrans = parentFragmentManager.beginTransaction()
            fragTrans.detach(it)
            fragTrans.attach(it)
            fragTrans.commit()
        }
        val user = UserSingelton.instance().user
        val coinRepository = CoinRepository(CryptoDatabase(requireActivity()))
        val favoriteViewModelFactory = FavoriteViewModelFactory(coinRepository)
        checkUser(user, favoriteViewModelFactory)
    }

    private fun checkUser(user: User?, vmf: FavoriteViewModelFactory){
        if (user != null) {
            handleUser(user, vmf)
        } else {
            handleNoUser()
        }
    }


    private fun handleUser(user: User, vmf: FavoriteViewModelFactory){
        binding.loginView.visibility = View.INVISIBLE
        binding.favoriteToLogin.visibility = View.INVISIBLE
        binding.favoriteToLoginButton.visibility = View.INVISIBLE
        binding.favoriteList.visibility = View.VISIBLE

        favoriteViewModel = ViewModelProvider(this, vmf).get(FavoriteViewModel::class.java)


        val adapter = FavoriteAdapter(CoinListener { coin ->
            favoriteViewModel.onCoinClicked(coin)
        })

        binding.favoriteList.adapter = adapter

        favoriteViewModel.getFavoriteCoins(user.id!!).observe(viewLifecycleOwner, Observer { favoriteCoins ->
            adapter.submitList(favoriteCoins)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val coin = adapter.currentList[position]
                favoriteViewModel.deleteCoin(coin)
                view?.let {
                    Snackbar.make(it, "Successfully deleted coin", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo") {
                            favoriteViewModel.saveCoin(coin, user.id!!)
                        }
                        show()
                    }
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.favoriteList)
        }

        favoriteViewModel.navigateToCoinDetail.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                this.findNavController().navigate(
                    FavoriteFragmentDirections
                        .actionFavoriteFragmentToCoinDetailFragment(coin))
                favoriteViewModel.onCoinDetailNavigated()
            }
        })
    }

    private fun handleNoUser() {
        binding.favoriteList.visibility = View.INVISIBLE
        binding.loginView.visibility = View.VISIBLE
        binding.favoriteToLogin.visibility = View.VISIBLE
        binding.favoriteToLoginButton.visibility = View.VISIBLE

        binding.favoriteToLoginButton.setOnClickListener {
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToUserActivity())
        }
    }
}