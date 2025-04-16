package com.example.feature.mylist

import androidx.lifecycle.ViewModel
import com.example.feature.mylist.sources.MyListDatabaseSource
import me.tatarka.inject.annotations.Inject

@Inject
internal class MyListViewModel(
    val databaseSource: MyListDatabaseSource
) : ViewModel()
