package com.example.demointerview.viewmodels

import androidx.lifecycle.ViewModel
import com.example.demointerview.model.Spot

class HomeViewModel : ViewModel() {
    fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        for (i in 0..5) {
            spots.add(
                Spot(
                    name = "Thong ${i + 1}",
                    city = "Kyoto",
                    url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
                )
            )
        }

        return spots
    }
}