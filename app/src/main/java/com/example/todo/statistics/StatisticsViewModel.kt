package com.example.todo.statistics

import android.app.Application
import androidx.lifecycle.*
import com.example.todo.data.Task
import com.example.todo.data.source.DefaultTasksRepository
import com.example.todo.data.Result.Success
import com.example.todo.data.Result
import com.example.todo.data.Result.Error
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    // Note, for testing and architecture purposes, it's bad practice to construct the repository
    // here. We'll show you how to fix this during the codelab
    private val tasksRepository = DefaultTasksRepository.getRepository(application)

    private val tasks: LiveData<Result<List<Task>>> = tasksRepository.observeTasks()
    private val _dataLoading = MutableLiveData<Boolean>(false)
    private val stats: LiveData<StatsResult?> = tasks.map {
        if (it is Success) {
            getActiveAndCompletedStats(it.data)
        } else {
            null
        }
    }

    val activeTasksPercent = stats.map {
        it?.activeTasksPercent ?: 0f
    }
    val completedTasksPercent: LiveData<Float> = stats.map { it?.completedTasksPercent ?: 0f }
    val dataLoading: LiveData<Boolean> = _dataLoading
    val error: LiveData<Boolean> = tasks.map { it is Error }
    val empty: LiveData<Boolean> =
        tasks.map { (it as? com.example.todo.data.Result.Success)?.data.isNullOrEmpty() }

    fun refresh() {
        _dataLoading.value = true
        viewModelScope.launch {
            tasksRepository.refreshTasks()
            _dataLoading.value = false
        }
    }
}