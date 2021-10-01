package com.cocos.ammymovie.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocos.ammymovie.R
import com.cocos.ammymovie.databinding.FragmentMainBinding
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.domain.model.MovieSection
import com.cocos.ammymovie.ui.common.AppState
import com.cocos.ammymovie.ui.common.MainViewModelFactory
import com.cocos.ammymovie.utils.*

class MainFragment : Fragment() {

    companion object {
        const val NUM_COLUMN = 2
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(requireActivity().application) }
    private var _binding: FragmentMainBinding? = null
    private val playNowAdapter = NowPlayingFragmentAdapter()
    private val upcomingAdapter = UpcomingFragmentAdapter()
    private var isLandscape = false
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
        var itemDecoration: DividerItemDecoration
        with(binding) {  // Создаем два списка
            isLandscape = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    recyclerPlaying.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                    recyclerUpcoming.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                    itemDecoration = dividerItemDecoration(
                        R.drawable.separator_vertical,
                        LinearLayoutManager.VERTICAL
                    )
                    true
                }
                else -> {
                    itemDecoration = dividerItemDecoration(
                        R.drawable.separator_horizontal,
                        LinearLayoutManager.HORIZONTAL
                    )
                    false
                }
            }
            recyclerPlaying.adapter = playNowAdapter
            recyclerUpcoming.adapter = upcomingAdapter
            recyclerPlaying.addItemDecoration(itemDecoration)
            recyclerUpcoming.addItemDecoration(itemDecoration)
        }

    }

    private fun dividerItemDecoration(resId: Int, orientation: Int): DividerItemDecoration {
        // Добавим разделитель
        val itemDecoration = DividerItemDecoration(requireContext(), orientation)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, resId, null)!!
        )
        return itemDecoration
    }

    private fun initViewModel() {
        viewModel.liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getDataFromLocalSource("ru-Ru")
    }

    private fun renderData(appState: AppState) = with(binding) {
        //Заполняем списки
        when (appState) {
            is AppState.Success -> {
                loadingLayout.hideIf { true }
                // используем функцию расширения вместо setData
                val movieComoList = appState.getMovieList()
                movieComoList.results?.let {
                    playNowAdapter.movieList = it.filter { movieDTO ->
                        movieDTO.section == MovieSection.PLAY.section
                    }

                    playNowAdapter.notifyDataSetChanged()
                    playNowAdapter.setOnItemClickListener { p -> openScreen(p)}

                    upcomingAdapter.movieListData = it.filter { movieDTO ->
                        movieDTO.section == MovieSection.UPCOMING.section
                    }
                    upcomingAdapter.notifyDataSetChanged()
                    upcomingAdapter.setOnItemClickListener { u -> openScreen(u) }
                    viewModel.saveMovieToDatabase(movieComoList)
                }

            }
            is AppState.Loading -> {
                loadingLayout.showIf { true }
            }
            is AppState.Error -> {
                loadingLayout.hideIf { true }
                mainView.showSnackBar(getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getDataFromLocalSource("ru-RU") })
                mainView.hideKeyboard()
            }
            else -> mainView.showSnackBar("Нет данных для загрузки")
        }
    }

    private fun openScreen(movie: MovieDTO) {
        navigateTo(requireActivity(),R.id.detailsFragment,
            Bundle().apply {
                    putParcelable("movie", movie)
                })
    }

    override fun onDestroyView() {
        _binding = null
        playNowAdapter.removeListener()
        super.onDestroyView()
    }
}