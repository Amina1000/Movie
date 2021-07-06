package com.example.ammymovie.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ammymovie.databinding.MainActivityBinding
import com.example.ammymovie.ui.main.view.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(getLayoutInflater())
        val view = binding.getRoot()
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
    }
}