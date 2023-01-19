package com.annisaarss.movieapp.data.lib

import com.nbs.nucleo.data.model.ApiError
import com.nbs.nucleo.utils.rx.operators.FlowableApiErrorOperator
import com.nbs.nucleo.utils.rx.operators.MaybeApiErrorOperator
import com.nbs.nucleo.utils.rx.operators.ObservableApiErrorOperator
import com.nbs.nucleo.utils.rx.operators.SingleApiErrorOperator

fun <T> getObservableApiError(): ObservableApiErrorOperator<T, ApiError> {
    return ObservableApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = { it.toApiError() })
}

fun <T> getSingleApiError(): SingleApiErrorOperator<T, ApiError> {
    return SingleApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = { it.toApiError() }
    )
}

fun <T> getFlowableApiError(): FlowableApiErrorOperator<T, ApiError> {
    return FlowableApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = { it.toApiError() }
    )
}

fun <T> getMaybeApiError(): MaybeApiErrorOperator<T, ApiError> {
    return MaybeApiErrorOperator(
        errorClazz = ApiError::class.java,
        mapObject = { it.toApiError() })
}

fun ApiError.toApiError(): ApiError {
    return ApiError(statusCode, message, errorCode)
}