package com.example.feature.home

import androidx.navigation.NavGraphBuilder
import com.example.core.di.ApplicationComponent
import com.example.feature.home.di.HomeDestinationComponent
import com.example.feature.home.di.create
import com.eygraber.vice.nav.viceComposable
import kotlinx.serialization.Serializable

@Serializable
data object Home

fun NavGraphBuilder.homeScreen(
    applicationComponent: ApplicationComponent,
    onGoToListClick: () -> Unit,
    onSearchResultClick: (String) -> Unit
) {
    viceComposable<Home> {
        HomeDestinationComponent::class.create(applicationComponent).factory(
            onGoToListClick,
            onSearchResultClick
        )
    }
}
