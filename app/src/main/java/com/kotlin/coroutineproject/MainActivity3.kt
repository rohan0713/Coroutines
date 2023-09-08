package com.kotlin.coroutineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        runBlocking {

            println("Thread: ${Thread.currentThread().name}")

            // Sequential Execution
            val time = measureTimeMillis {
                val msg1 = sendMessageOne()
                val msg2 = sendMessageTwo()
            }
            println("Sequential execution Completed in $time ms")


            // Concurrent Execution
            val time1 = measureTimeMillis {
                val msg1 : Deferred<String> = async { sendMessageOne() }
                val msg2 : Deferred<String> = async { sendMessageTwo() }
                println("${msg1.await()}, ${msg2.await()}")
            }

            println("Parallel execution done in $time1 ms")

            // Lazy Execution
            val time2 = measureTimeMillis {
                val msg1 : Deferred<String> = async(start = CoroutineStart.LAZY) { sendMessageOne() }
                val msg2 : Deferred<String> = async(start = CoroutineStart.LAZY) { sendMessageTwo() }
                println("${msg1.await()}, ${msg2.await()}")
            }
        }
    }

    private suspend fun sendMessageOne() : String {
        delay(1000)
        println("running.........")
        return "hello"
    }

    private suspend fun sendMessageTwo() : String {
        delay(1000)
        return "world"
    }
}