package com.example.ammymovie.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ammymovie.R
import com.example.ammymovie.databinding.FragmentMainBinding
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.service.MainBroadcastReceiver
import com.example.ammymovie.ui.common.AppState
import com.example.ammymovie.ui.detail.DetailsFragment
import com.example.ammymovie.utils.hideIf
import com.example.ammymovie.utils.hideKeyboard
import com.example.ammymovie.utils.showIf
import com.example.ammymovie.utils.showSnackBar

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

    //Урок 6 создаем объект ресивера
    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Урок 6
        // регистрируем ресивер смены языка.
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(receiver, IntentFilter(Intent.ACTION_LOCALE_CHANGED))
        }
    }

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
        with(binding){  // Создаем два списка
        isLandscape = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                recyclerPlaying.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                recyclerUpcoming.layoutManager = GridLayoutManager(context, NUM_COLUMN)
                itemDecoration = dividerItemDecoration(R.drawable.separator_vertical,LinearLayoutManager.VERTICAL)
                true
            }
            else -> {
                itemDecoration = dividerItemDecoration(R.drawable.separator_horizontal,LinearLayoutManager.HORIZONTAL)
                false
            }
        }
            recyclerPlaying.adapter = adapterPlayNow
            recyclerUpcoming.adapter = adapterUpcoming
            recyclerPlaying.addItemDecoration(itemDecoration)
            recyclerUpcoming.addItemDecoration(itemDecoration)
        }

    }

    private fun dividerItemDecoration(resId:Int,orientation: Int): DividerItemDecoration {
        // Добавим разделитель
        val itemDecoration = DividerItemDecoration(requireContext(), orientation)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources,resId, null)!!
        )
        return itemDecoration
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getDataFromLocalSource("ru-Ru")
    }

    private fun renderData(appState: AppState) = with(binding){
        //Заполняем списки
        when (appState) {
            is AppState.SuccessPlay -> {
                val movieDataPlay = appState.movieDataPlay
                loadingLayout.hideIf {true}
                // используем функцию расширения вместо setData
                adapterPlayNow.also {
                    it.movieData = movieDataPlay
                    it.notifyDataSetChanged()
                }
                adapterPlayNow.setOnItemClickListener { openScreen(it) }
            }
            is AppState.SuccessCome -> {
                val movieDataCome = appState.movieDataCome
                loadingLayout.hideIf {true}
                // используем функцию расширения вместо setData
                adapterUpcoming.also {
                    it.movieData = movieDataCome
                    it.notifyDataSetChanged()
                    adapterUpcoming.setOnItemClickListener { openScreen(it) }
                }
            }
            is AppState.Loading -> {
                loadingLayout.showIf {true}
            }
            is AppState.Error -> {
                loadingLayout.hideIf {true}
                mainView.showSnackBar(getString(R.string.error)
                    ,getString(R.string.reload)
                    ,{viewModel.getDataFromLocalSource("ru-RU")})
                mainView.hideKeyboard()
            }else-> mainView.showSnackBar("Нет данных для загрузки")
        }
    }

    private fun openScreen(movie: MovieDTO) {
        val manager = activity?.supportFragmentManager
        manager?.let {
            manager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(Bundle()
                    .apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                    })
                )
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        adapterPlayNow.removeListener()
        super.onDestroyView()
    }

    override fun onDestroy() {
        //Урок 6
        //Не забываем снять подписку на события, когда они уже никому не нужны.
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(receiver)
        }
        super.onDestroy()
    }
}