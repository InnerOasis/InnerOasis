package com.example.inneroasis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, YoutubeVideoFragment(), null).commit()

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