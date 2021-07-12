package com.example.ammymovie.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.ammymovie.R
import com.example.ammymovie.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initButtons()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun initButtons() {
        binding.btnHome.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.setBackgroundResource(
            when {
                isChecked -> R.drawable.ic_baseline_home_20
                else  -> R.drawable.ic_baseline_home_20_off
            })
        }
        binding.btnFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.setBackgroundResource(
            when {
                isChecked -> R.drawable.ic_baseline_star_border_20
                else  -> R.drawable.ic_baseline_star_border_20_off
            })
        }
        binding.btnRating.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.setBackgroundResource(
            when {
                isChecked -> R.drawable.ic_baseline_graphic_eq_20
                else  -> R.drawable.ic_baseline_graphic_eq_20_off
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Здесь определяем меню приложения (активити)
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.action_search) // поиск пункта меню поиска
        val searchText = search?.actionView as SearchView // строка поиска
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // реагирует на конец ввода поиска
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            // реагирует на нажатие каждой клавиши
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return true
    }
}


