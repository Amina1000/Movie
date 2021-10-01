package com.cocos.ammymovie

import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.cocos.ammymovie.databinding.MainActivityBinding
import com.cocos.ammymovie.utils.navigateTo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNavigationMenu()
    }

    private fun initNavigationMenu() {

        when {
            resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE -> {
                binding.navigation?.setOnNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.item_home -> {
                            navigateTo(this@MainActivity,R.id.mainFragment)
                            true
                        }
                        R.id.item_favorites -> {
                            navigateTo(this@MainActivity,R.id.favoriteFragment)
                            true
                        }
                        R.id.item_ratings -> {
                            navigateTo(this@MainActivity,R.id.ratingsFragment)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Здесь определяем меню приложения (активити)
        menuInflater.inflate(R.menu.main_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search) // поиск пункта меню поиска
        val searchText = searchItem?.actionView as SearchView // строка поиска
        searchText.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchText.clearFocus()
                searchText.setQuery("", false)
                searchItem.collapseActionView()
                val bundle = Bundle()
                bundle.putString("search", query?.trim())
                navigateTo(this@MainActivity,R.id.searchFragment, bundle)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO("Not yet implemented")
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                navigateTo(this@MainActivity,R.id.settingsFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}


