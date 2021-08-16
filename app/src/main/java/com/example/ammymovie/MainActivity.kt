package com.example.ammymovie

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.ammymovie.databinding.MainActivityBinding
import com.example.ammymovie.ui.main.MainFragment
import com.example.ammymovie.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNavigationMenu()
        when (savedInstanceState) {
            null -> loadFragment(MainFragment.newInstance())
        }
    }

    private fun initNavigationMenu() {

        when {
            resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE -> {
                binding.navigation?.setOnNavigationItemSelectedListener {
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
        val searchItem = menu?.findItem(R.id.action_search) // поиск пункта меню поиска
        val searchText = searchItem?.actionView as SearchView // строка поиска
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {
                loadFragment(SettingsFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


