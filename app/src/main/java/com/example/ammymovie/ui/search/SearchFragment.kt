package com.example.ammymovie.ui.search

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
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentSearchBinding
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.model.MovieSection
import com.example.ammymovie.ui.common.AppState
import com.example.ammymovie.ui.common.SearchViewModelFactory
import com.example.ammymovie.ui.detail.DetailsFragment
import com.example.ammymovie.utils.hideIf
import com.example.ammymovie.utils.hideKeyboard
import com.example.ammymovie.utils.showIf
import com.example.ammymovie.utils.showSnackBar

private const val BUNDLE_EXTRA = "search"

class SearchFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = SearchFragment().apply { arguments = bundle }
    }

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(requireActivity().application) }
    private var _binding: FragmentSearchBinding? = null
    private val searchAdapter = SearchFragmentAdapter()
    private var isLandscape = false
    private val query: String? by lazy { arguments?.getString(BUNDLE_EXTRA, "") }
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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
            isLandscape =
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    recyclerSearch.layoutManager = GridLayoutManager(context, 4)
                    true
                } else {
                    recyclerSearch.layoutManager = GridLayoutManager(context, 2)
                    false
                }
            itemDecoration = dividerItemDecoration(
                R.drawable.separator_vertical,
                LinearLayoutManager.VERTICAL
            )
            recyclerSearch.adapter = searchAdapter
            recyclerSearch.addItemDecoration(itemDecoration)
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
        query?.let {
            viewModel.getSearchingMovieServer("ru-RU", it)
        }

    }

    private fun renderData(appState: AppState) = with(binding) {
        //Заполняем списки
        when (appState) {
            is AppState.Success -> {
                loadingLayout.hideIf { true }
                // используем функцию расширения вместо setData
                appState.movieDataList.results?.let {
                    searchAdapter.movieListData = it
                    searchAdapter.notifyDataSetChanged()
                    searchAdapter.setOnItemClickListener { p -> openScreen(p) }

                }
            }
            is AppState.Loading -> {
                loadingLayout.showIf { true }
            }
            is AppState.Error -> {
                loadingLayout.hideIf { true }
                searchView.showSnackBar(getString(R.string.error),
                    getString(R.string.reload),
                    {
                        query?.let {q->
                            viewModel.getSearchingMovieServer("ru-RU", q)
                        }
                    })
                searchView.hideKeyboard()
            }
            else -> searchView.showSnackBar("Нет данных для загрузки")
        }
    }

    private fun openScreen(movie: MovieDTO) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container, DetailsFragment.newInstance(Bundle()
                .apply {
                    putParcelable("movie", movie)
                })
        )?.addToBackStack("")?.commitAllowingStateLoss()
    }
}