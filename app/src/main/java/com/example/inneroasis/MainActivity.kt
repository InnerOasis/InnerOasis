package com.example.inneroasis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inneroasis.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val favoritesFragment: Fragment = FavoriteFragment()
        val browseFragment: Fragment = BrowseFragment()
        val searchFragment: Fragment = SearchFragment()
        val stressEvalFragment: Fragment = StressEvalFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handles navigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_browse -> fragment = browseFragment
                R.id.nav_favorites -> fragment = favoritesFragment
                R.id.nav_search -> fragment = searchFragment
                R.id.nav_evaluation -> fragment = stressEvalFragment
            }
            replaceFragment(fragment)
            true
        }

        // default navigation upon opening app
        bottomNavigationView.selectedItemId = R.id.nav_browse

    }

    private fun replaceFragment(entryFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.entry_frame, entryFragment)
        fragmentTransaction.commit()
    }
}
