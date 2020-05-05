package com.example.todo.statistics

import com.example.todo.data.Task
import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*

class StatisticsUtilsKtTest {

    @Test
    fun getActiveAndCompletedStatsa() {
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

// Becomes

        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat( result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletdedStatsa() {
        val tasks = emptyList<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }


    @Test
    fun getActiveAndCompletdedStahtsa() {
        val tasks = null

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }
}