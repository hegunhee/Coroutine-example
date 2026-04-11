package blueberry.gunhee.coroutine_example.chapter1

import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 스레드는 스택 메모리를 가지고 있고
 * 스레드 수가 늘어면 메모리 공간이 부족해지므로 갈수록 느려짐
 *
 * 그렇기때문에 스레드 풀을 사용함 (재활용)
 *
 * 그에 반해서 코루틴은 객체이고 가지고있는 메모리가 매우 작거나 없어서
 * 저렴하고 빠르게 만들 수 있음
 */
fun main() = runBlocking {
    val threads = mutableListOf<Thread>()
    val threadTimes = measureTimeMillis {
        for(i in 0 until 100_000) {
            threads += Thread {}
            threads.last().start()
        }
    }
    threads.forEach { it.join() }
    println("create 100000 thread Time $threadTimes")


    val jobs = mutableListOf<Job>()
    val coroutineTimes = measureTimeMillis {
        for(i in 0 until 100_000) {
            jobs += launch {}
        }
    }

    // joinAll은 Collection<Job>의 확장함수
    jobs.joinAll()
    println("create 100000 coroutine time $coroutineTimes")
}
