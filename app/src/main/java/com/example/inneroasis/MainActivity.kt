package com.example.inneroasis

import SampleAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView1: RecyclerView = findViewById(R.id.sensoryRecyclerView)
        recyclerView1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataList1 = generateSampleData()
        val adapter1 = SampleAdapter(dataList1)
        recyclerView1.adapter = adapter1

        val recyclerView2: RecyclerView = findViewById(R.id.natureRecyclerView)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataList2 = generateSampleData()
        val adapter2 = SampleAdapter(dataList2)
        recyclerView2.adapter = adapter2


        val recyclerView3: RecyclerView = findViewById(R.id.recommendRecyclerView)
        recyclerView3.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataList3 = generateSampleData()
        val adapter3 = SampleAdapter(dataList2)
        recyclerView3.adapter = adapter3


        val recyclerView: RecyclerView = findViewById(R.id.relaxingRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataList = generateSampleData()
        val adapter = SampleAdapter(dataList)
        recyclerView.adapter = adapter

    }

    private fun generateSampleData(): List<SampleData> {
        val dataList = mutableListOf<SampleData>()
        for (i in 1..10) {
            dataList.add(SampleData("Item $i"))
        }
        return dataList
    }
}