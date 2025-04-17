package com.example.feature.mylist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.core.di.ApplicationComponent
import com.example.feature.mylist.di.MyListDestinationComponent
import com.example.feature.mylist.di.create
import com.eygraber.vice.nav.viceComposable
import kotlinx.serialization.Serializable

@Serializable
private data object MyList

fun NavController.navigateToMyList() =
    navigate(route = MyList)

fun NavGraphBuilder.myListScreen(
    applicationComponent: ApplicationComponent,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    viceComposable<MyList> {
        MyListDestinationComponent::class.create(applicationComponent).factory(
            onBackClick,
            onItemClick
        )
    }
}
