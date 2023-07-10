package com.ponyo.presentation.uistate

import com.ponyo.domain.entity.LocalInfo


data class LocalInfoUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val localInfoList: List<LocalInfo> = emptyList()
) {
    companion object {
        val Uninitialized =
            LocalInfoUiState(
                isLoading = false,
                isError = false,
                localInfoList = emptyList()
            )
    }
}

