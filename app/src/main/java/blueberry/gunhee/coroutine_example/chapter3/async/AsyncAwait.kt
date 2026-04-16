package blueberry.gunhee.coroutine_example.chapter3.async

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * notAwait start
 * Exception in thread "main" kotlin.NotImplementedError: An operation is not implemented: Not implemented yet!
 * 	at blueberry.gunhee.coroutine_example.chapter3.async.AsyncAwaitKt$main$1$deferredNotAwait$1.invokeSuspend
 *
 * 	await 함수를 호출해야 async 안의 구문이 실행됨
 */
fun main() = runBlocking {
    val deferredNotAwait = async {
        println("notAwait start")
        TODO("Not implemented yet!")
        println("notAwait end")
    }

    // Wait for it to fall
    delay(2000)

    val deferredAwait = async {
        println("deferredAwait start")
        TODO("Not implemented yet!")
        println("deferredAwait end")
    }

    deferredAwait.await()
}
