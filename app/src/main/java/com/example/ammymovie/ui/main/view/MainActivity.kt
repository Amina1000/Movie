package com.example.ammymovie.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.ammymovie.R
import com.example.ammymovie.databinding.MainActivityBinding
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNavigationMenu()
        when (savedInstanceState) {
            null ->loadFragment(MainFragment.newInstance())
        }
    }

    private fun initNavigationMenu() {
        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    loadFragment(MainFragment.newInstance())
                    true
                }
                R.id.item_favorites -> {
                    loadFragment(MainFragment.newInstance())
                    true
                }
                R.id.item_ratings -> {
                    loadFragment(MainFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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


