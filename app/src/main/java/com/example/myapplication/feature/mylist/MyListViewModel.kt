package com.example.myapplication.feature.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feature.mylist.sources.MyListDatabaseSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    val databaseSource: MyListDatabaseSource
) : ViewModel() {
    fun insert() {
        viewModelScope.launch {
            databaseSource.insert()
        }
    }

    fun clear() {
        viewModelScope.launch {
            databaseSource.deleteAll()
        }
    }
}
