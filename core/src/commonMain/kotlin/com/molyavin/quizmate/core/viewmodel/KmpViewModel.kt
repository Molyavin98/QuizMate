package com.molyavin.quizmate.core.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

open class KmpViewModel {
    protected val viewModelScope: CoroutineScope = MainScope()

    open fun onCleared() {
        viewModelScope.cancel()
    }
}
