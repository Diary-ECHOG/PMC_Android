package com.app.pmc.feat.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(), ContainerHost<HomeUiState, Unit> {
    override val container: Container<HomeUiState, Unit> = container(HomeUiState())

    init {}
}