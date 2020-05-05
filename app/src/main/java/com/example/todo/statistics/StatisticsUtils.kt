package com.example.todo.statistics

import com.example.todo.data.Task


internal fun getActiveAndCompletedStats(tasks: List<Task>?): StatsResult {
    return if (tasks == null ||  tasks.isEmpty()){
        StatsResult(0f, 0f)
    } else {
        val totalTasks = tasks!!.size
        val numberOfActiveTasks = tasks.count { it.isActive }
        val activePercent = 100 * numberOfActiveTasks / totalTasks
        val completedTasksPercent = 100 * (totalTasks - numberOfActiveTasks) / totalTasks
       StatsResult(
            activeTasksPercent = activePercent.toFloat(),
            completedTasksPercent = completedTasksPercent.toFloat()
        )
    }
}

data class StatsResult(val activeTasksPercent: Float, val completedTasksPercent: Float)