package com.example.ammymovie.ui.main.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentMainBinding
import com.example.ammymovie.ui.main.model.Movie
import com.example.ammymovie.ui.main.viewmodel.AppState
import com.example.ammymovie.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        const val NUM_COLUMN=2
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val adapterPlayNow = NowPlayingFragmentAdapter()
    private val adapterUpcoming = UpcomingFragmentAdapter()
    private var isLandscape =false
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация данных
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        with(binding){  // Создаем два списка
        isLandscape = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                recyclerPlaying.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                recyclerUpcoming.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                true
            }
            else -> false
        }

            recyclerPlaying.adapter = adapterPlayNow
            recyclerUpcoming.adapter = adapterUpcoming
            val itemDecoration = dividerItemDecoration()
            recyclerPlaying.addItemDecoration(itemDecoration)
            recyclerUpcoming.addItemDecoration(itemDecoration)
        }

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
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getDataFromLocalSource()
    }

    private fun renderData(appState: AppState) = with(binding){
        //Заполняем списки
        when (appState) {
            is AppState.Success -> {
                val movieDataPlay = appState.movieDataPlay
                val movieDataCome = appState.movieDataCome
                loadingLayout.hideIf {true}
                // используем функцию расширения вместо setData
                adapterPlayNow.also {
                    it.movieData = movieDataPlay
                    it.notifyDataSetChanged()
                }
                adapterUpcoming.also {
                    it.movieData = movieDataCome
                    it.notifyDataSetChanged()
                }
                // реализация метода при помощи лямбда, it - объект типа Movie
                adapterPlayNow.setOnItemClickListener{openScreen(it)}
                adapterUpcoming.setOnItemClickListener {openScreen(it)}
            }
            is AppState.Loading -> {
                loadingLayout.showIf {true}
            }
            is AppState.Error -> {
                loadingLayout.hideIf {true}
                mainView.showSnackBar(getString(R.string.error)
                    ,getString(R.string.reload)
                    ,{viewModel.getDataFromLocalSource()})
                mainView.hideKeyboard()
            }
        }
    }

    private fun openScreen(movie: Movie) {
        val manager = activity?.supportFragmentManager
        manager?.let {
            manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(Bundle()
                    .apply {
                    putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                }))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapterPlayNow.removeListener()
    }
}