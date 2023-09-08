package com.kotlin.coroutineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        println("Thread: ${Thread.currentThread().name}")

        runBlocking {

            withTimeout(2000) {
                try {
                    for (i in 1..100) {
                        println("$i")
                        delay(1000)
                    }
                } catch (ex: TimeoutCancellationException) {
                    println("Catch block")
                } finally {
                    println("finally block")
                }
            }
        }

        runBlocking {
           val result : String? = withTimeoutOrNull(2000){
                for (i in 1..100) {
                    println("$i")
                    delay(1000)
                }
                "Done"
            }
            println("Result: $result")
        }

        println("Thread: ${Thread.currentThread().name}")

    }
}