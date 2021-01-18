package com.emrekose.videogames.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchSharedViewModel: ViewModel() {
    val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(query: String?) {
        searchQuery.value = query
    }
}