package com.example.myapplication.feature.mylist

import androidx.lifecycle.ViewModel
import com.example.myapplication.feature.mylist.sources.MyListDatabaseSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    val databaseSource: MyListDatabaseSource
) : ViewModel()
