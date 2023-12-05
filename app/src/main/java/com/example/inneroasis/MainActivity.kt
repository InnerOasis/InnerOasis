package com.example.inneroasis


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

        val showStressButton: Button = findViewById(R.id.showStressButton)
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

        showStressButton.setOnClickListener {
            Log.d("MainActivity", "Button clicked")
            // Call the function in the current fragment or a global way
            val currentFragment = supportFragmentManager.findFragmentById(R.id.entry_frame)
            if (currentFragment is StressEvalFragment) {
                currentFragment.showStressRatingDialog()
            }
        }

    }


    private fun replaceFragment(entryFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.entry_frame, entryFragment)
        fragmentTransaction.commit()
    }

    
    fun showStressButton() {
        findViewById<Button>(R.id.showStressButton).visibility = View.VISIBLE
    }

    fun hideStressButton() {
        findViewById<Button>(R.id.showStressButton).visibility = View.GONE
    }



    }
