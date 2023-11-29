package com.example.inneroasis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.inneroasis.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val favoritesFragment: Fragment = FavoriteFragment()
        val browseFragment: Fragment = BrowseFragment()
        val searchFragment: Fragment = SearchFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_browse -> fragment = browseFragment
                R.id.nav_favorites -> fragment = favoritesFragment
                R.id.nav_search -> fragment = searchFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_browse

    }

    private fun replaceFragment(entryFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.entry_frame, entryFragment)
        fragmentTransaction.commit()
    }
}


//        val recyclerView1: RecyclerView = findViewById(R.id.sensoryRecyclerView)
//        recyclerView1.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
////        val adapter1 = YoutubeRecyclerViewAdapter(youtubeVideos = , context = )
////        recyclerView1.adapter = adapter1
//
//        val recyclerView2: RecyclerView = findViewById(R.id.natureRecyclerView)
//        recyclerView2.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        val dataList2 = generateSampleData()
//        val adapter2 = SampleAdapter(dataList2)
//        recyclerView2.adapter = adapter2
//
//        val recyclerView: RecyclerView = findViewById(R.id.relaxingRecyclerView)
//        recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        val dataList = generateSampleData()
//        val adapter = SampleAdapter(dataList)
//        recyclerView.adapter = adapter
//
//    }
//
//    // comment teresa branch
//    private fun generateSampleData(): List<SampleData> {
//        val dataList = mutableListOf<SampleData>()
//        for (i in 1..10) {
//            dataList.add(SampleData("Item $i"))
//        }
//        return dataList
//    }
//}