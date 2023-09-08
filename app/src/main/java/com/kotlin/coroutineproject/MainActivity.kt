package com.kotlin.coroutineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Thread name: ${Thread.currentThread().name}")

        // Coroutine Builder
        // Global Scope Coroutine survive the entire life of the application
        val j : Job = GlobalScope.launch {
            delay(3000)
            println("Thread name: ${Thread.currentThread().name}")
        }

        runBlocking {

            withContext(Dispatchers.IO) {
                delay(1000)
                println("Thread name in IO: ${Thread.currentThread().name}")
            }

//            delay() is a suspend function that will suspend the coroutine without blocking the thread
//            Other coroutines on the same thread will continue working
            j.join()
            println("Thread in run blocking: ${Thread.currentThread().name}")
        }

        // Return a reference to the Deferred<T> object
        // Using the Deferred object you can cancel the coroutine,
        // wait for the coroutine to finish, or retrieve the returned result
        val job: Deferred<String> = GlobalScope.async {
            println("Thread in async: ${Thread.currentThread().name}")
            "Rohan"
        }

        Thread.sleep(1000)
        runBlocking {
            println("Thread return: ${job.await()}")
        }

        println("Thread name: ${Thread.currentThread().name}")
    }
}