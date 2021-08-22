package com.cocos.ammymovie.ui.favorite

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocos.ammymovie.R
import com.cocos.ammymovie.databinding.FragmentCommonBinding
import com.cocos.ammymovie.domain.model.MovieDTO
import com.cocos.ammymovie.ui.common.AppState
import com.cocos.ammymovie.ui.common.CommonFragmentAdapter
import com.cocos.ammymovie.ui.common.LocalMovieViewModel
import com.cocos.ammymovie.ui.detail.DetailsFragment
import com.cocos.ammymovie.utils.hideIf
import com.cocos.ammymovie.utils.hideKeyboard
import com.cocos.ammymovie.utils.showIf
import com.cocos.ammymovie.utils.showSnackBar

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private lateinit var viewModel: LocalMovieViewModel
    private var _binding: FragmentCommonBinding? = null
    private val adapter = CommonFragmentAdapter()
    private var isLandscape = false
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация данных
        initRecycler()
        initViewModel()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LocalMovieViewModel::class.java)
        viewModel.localLiveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getMovieFromLocal()
    }

    private fun initRecycler() {
        var itemDecoration: DividerItemDecoration
        with(binding) {  // Создаем два списка
            isLandscape =
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    recyclerCommon.layoutManager = GridLayoutManager(context, 4)
                    true
                } else {
                    recyclerCommon.layoutManager = GridLayoutManager(context, 2)
                    false
                }
            itemDecoration = dividerItemDecoration(
                R.drawable.separator_vertical,
                LinearLayoutManager.VERTICAL
            )
            recyclerCommon.adapter = adapter
            recyclerCommon.addItemDecoration(itemDecoration)
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


    private fun renderData(appState: AppState) = with(binding) {
        //Заполняем списки
        when (appState) {
            is AppState.Success -> {
                loadingLayout.hideIf { true }
                // используем функцию расширения вместо setData
                appState.movieDataList.results?.let {
                    adapter.movieListData = it.filter { movieDTO -> movieDTO.favorite }
                    adapter.notifyDataSetChanged()
                    adapter.setOnItemClickListener { p -> openScreen(p) }

                }
            }
            is AppState.Loading -> {
                loadingLayout.showIf { true }
            }
            is AppState.Error -> {
                loadingLayout.hideIf { true }
                commonView.showSnackBar(getString(R.string.error),
                    getString(R.string.reload),
                    {viewModel.getMovieFromLocal()})
                commonView.hideKeyboard()
            }
            else -> commonView.showSnackBar("Нет данных для загрузки")
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