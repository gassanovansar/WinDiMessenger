package com.example.corekt

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    inline fun fold(failure: (L) -> Unit = {}, success: (R) -> Any = {}): Any =
        when (this) {
            is Left -> failure(a)
            is Right -> success(b)
        }
}

inline fun <L, R> Either<L, R>.doOnSuccess(action: (R) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> {
            Either.Left(a)
        }

        is Either.Right -> {
            action(b)
            Either.Right(b)
        }
    }

inline fun <L, R> Either<L, R>.doOnFailure(failure: (L) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> {
            failure.invoke(a)
            Either.Left(a)
        }

        is Either.Right -> Either.Right(b)
    }