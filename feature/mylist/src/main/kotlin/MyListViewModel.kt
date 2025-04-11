package com.example.feature.mylist

import androidx.lifecycle.ViewModel
import com.example.feature.mylist.sources.MyListDatabaseSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MyListViewModel @Inject constructor(
    val databaseSource: MyListDatabaseSource
) : ViewModel()
