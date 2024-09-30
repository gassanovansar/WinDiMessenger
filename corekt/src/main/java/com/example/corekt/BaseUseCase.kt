package com.example.corekt

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, out Type : Any> {
    abstract suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Type>

    open suspend operator fun invoke(scope: CoroutineScope, params: Params): Either<Failure, Type> {
        val deferred = scope.async { execute(params, this) }
        return withContext(scope.coroutineContext) {
            try {
                deferred.await()
            } catch (e: Exception) {
                Either.Left(Failure.UseCase(e))
            }
        }
    }
}