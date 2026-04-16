package blueberry.gunhee.coroutine_example.chapter3.example

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * logging something...
 * firebase auth doing....
 * DB insert something...
 * this is LoggingException logging fail
 * Job cancelled due to logging fail [[blueberry.gunhee.coroutine_example.chapter3.example.ExceptionHandlerExampleKt$special$$inlined$CoroutineExceptionHandler$1@4b553d26, StandaloneCoroutine{Cancelling}@2a556333, BlockingEventLoop@7d70d1b1]]
 * this is FirebaseException firebase auth fail
 * Job cancelled due to firebase auth fail [[blueberry.gunhee.coroutine_example.chapter3.example.ExceptionHandlerExampleKt$special$$inlined$CoroutineExceptionHandler$1@4b553d26, StandaloneCoroutine{Cancelling}@3dfc5fb8, BlockingEventLoop@7d70d1b1]]
 * this is DBExcpetion DB insert fail...
 * Job cancelled due to DB insert fail... [[blueberry.gunhee.coroutine_example.chapter3.example.ExceptionHandlerExampleKt$special$$inlined$CoroutineExceptionHandler$1@4b553d26, StandaloneCoroutine{Cancelling}@4d50efb8, BlockingEventLoop@7d70d1b1]]
 *
 * exception에 대한 에러 핸들링을 한곳에서 몰 수 있다.
 */
private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    when(throwable) {
        is LoggingException -> {
            println("this is LoggingException ${throwable.message}")
        }
        is FirebaseException -> {
            println("this is FirebaseException ${throwable.message}")
        }
        is DBException -> {
            println("this is DBExcpetion ${throwable.message}")
        }
    }
    println("Job cancelled due to ${throwable.message} [$coroutineContext]")
}

fun main() = runBlocking {
    launch(exceptionHandler + Job()) {
        println("logging something...")
        delay(300L)
        throw LoggingException("logging fail")
    }
    launch(exceptionHandler + Job()) {
        println("firebase auth doing....")
        delay(500L)
        throw FirebaseException("firebase auth fail")
    }
    launch(exceptionHandler + Job()) {
        println("DB insert something...")
        delay(1000L)
        throw DBException("DB insert fail...")
    }

    delay(2000)
}



class LoggingException(override val message: String) : RuntimeException()

class FirebaseException(override val message: String) : RuntimeException()

class DBException(override val message: String) : RuntimeException()
