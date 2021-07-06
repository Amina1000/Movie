package com.example.ammymovie.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ammymovie.R
import com.example.ammymovie.databinding.MainFragmentBinding
import com.example.ammymovie.ui.main.viewmodel.AppState
import com.example.ammymovie.ui.main.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val adapterPlayNow = NowPlayingFragmentAdapter()
    private val adapterUpcoming = UpcomingFragmentAdapter()

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        val recyclerviewPlaying = binding.recyclerPlaying
        val recyclerviewUpcoming = binding.recyclerUpcoming

        recyclerviewPlaying.adapter = adapterPlayNow
        recyclerviewUpcoming.adapter = adapterUpcoming
        val itemDecoration = dividerItemDecoration()
        recyclerviewPlaying.addItemDecoration(itemDecoration)
        recyclerviewUpcoming.addItemDecoration(itemDecoration)
    }

    private fun dividerItemDecoration(): DividerItemDecoration {
        // Добавим разделитель
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.HORIZONTAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator, null)!!
        )
        return itemDecoration
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getDataFromLocalSource()
    }

    private fun renderData(appState: AppState) {
        val loadingLayout = binding.loadingLayout
        val mainView = binding.mainView
        when (appState) {
            is AppState.Success -> {
                val movieDataPlay = appState.movieDataPlay
                val movieDataCome = appState.movieDataCome
                loadingLayout.visibility = View.GONE
                adapterPlayNow.setData(movieDataPlay)
                adapterUpcoming.setData(movieDataCome)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                    .make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getDataFromLocalSource() }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}