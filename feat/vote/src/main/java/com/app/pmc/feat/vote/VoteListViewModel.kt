package com.app.pmc.feat.vote

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class VoteListViewModel @Inject constructor() : ViewModel(), ContainerHost<VoteListUiState, Unit> {
    override val container: Container<VoteListUiState, Unit> = container(VoteListUiState())
}