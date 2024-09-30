package com.example.core.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.corekt.Either
import com.example.corekt.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

abstract class BaseScreenModel<State : Any, Event : Any>(initState: State) : ScreenModel,
    KoinComponent {

    private val ioScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    private val _event = Channel<Event>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val _status = MutableStateFlow(false)
    val status = _status.asStateFlow()


    protected fun setState(state: () -> State) {
        _state.value = state()
    }

    protected fun setEvent(newEvent: Event) {
        screenModelScope.launch {
            _event.send(newEvent)
        }
    }

    protected fun setStatus(status: Boolean) {
        screenModelScope.launch {
            _status.value = status
        }
    }

    /**
     * Выполнить запросы
     * @param operation - описываем запрос
     * @param loading - статус загрузки(можем переопределить)
     * @param failure - подписка на ошибку(можем переопределить)
     * @param success - результат
     */
    protected fun <T> launchOperation(
        operation: suspend (CoroutineScope) -> Either<Failure, T>,
        loading: (Boolean) -> Unit = { setStatus(it) },
        failure: (Failure) -> Unit = {},
        success: (T) -> Unit = {}
    ) {
        loading.invoke(true)
        screenModelScope.launch {
            withContext(ioScope.coroutineContext) {
                operation(this)
            }.fold(failure, success)
        }
        loading.invoke(false)
    }

}