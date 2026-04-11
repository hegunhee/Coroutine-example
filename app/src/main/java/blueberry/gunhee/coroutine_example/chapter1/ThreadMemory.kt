package blueberry.gunhee.coroutine_example.chapter1

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * [0.383s][warning][os,thread] Failed to start thread "Unknown thread" - pthread_create failed (EAGAIN) for attributes: stacksize: 2048k, guardsize: 16k, detached.
 * [0.383s][warning][os,thread] Failed to start the native thread for java.lang.Thread "Thread-4074"
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
 * 해당 로그를 보면 스택메모리의 크기는 2MB인데
 * 제한은 4GB라서 중간에 OOM이 발생되기 시작
 *
 * before coroutines memory: 12MB
 * during coroutines memory: 13MB
 * coroutine time: 11 ms
 * after coroutines memory: 14MB
 * 코루틴메모리 사용량
 */
fun main() = runBlocking {

    val threads = mutableListOf<Thread>()

    printMemory("before threads")

    val time = measureTimeMillis {
        repeat(10000) {
            val t = Thread {
                Thread.sleep(1000L)
            }
            threads += t
            t.start()
        }
    }

    // 진짜 사용량
    printMemory("during threads")

    threads.forEach { it.join() }

    println("thread time: $time ms")
    printMemory("after threads")

    val jobs = mutableListOf<Job>()

    printMemory("before coroutines")

    val coroutineTime = measureTimeMillis {
        repeat(1000) {
            jobs += launch {
                delay(1000L)
            }
        }
    }

    printMemory("during coroutines")

    jobs.joinAll()

    println("coroutine time: $coroutineTime ms")
    printMemory("after coroutines")
}

fun printMemory(tag: String) {
    val runtime = Runtime.getRuntime()
    val used = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)
    println("$tag memory: ${used}MB")
}
