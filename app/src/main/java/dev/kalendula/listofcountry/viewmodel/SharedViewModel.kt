package dev.kalendula.listofcountry.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dev.kalendula.listofcountry.data.Repository

class SharedViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    val isLoading = ObservableBoolean()

    init {
        repository.getData()
    }

    fun getLd() = repository.getLd()

    fun onRefresh() {
        isLoading.set(true)
        repository.update()
    }
}
