package lob.study.kotlin.global.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import kotlin.streams.toList


@RestControllerAdvice
class GlobalExceptionAdvice {

    private var log = LoggerFactory.getLogger(GlobalExceptionAdvice::class.java)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value(),
            exception = exception::class.simpleName,
            message = exception.localizedMessage
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value(),
            exception = exception::class.simpleName,
            message = exception.localizedMessage
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val fieldErrors: List<FieldError> = exception.bindingResult.fieldErrors
                .stream()
                .map { error -> FieldError(field = error.field, errorCode = error.code) }.toList()
        val errorResponse = ErrorResponse(
            httpStatus = HttpStatus.BAD_REQUEST.value(),
            exception = exception::class.simpleName,
            message = exception.localizedMessage,
            fieldErrors = fieldErrors
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleThrowable(exception: Exception): ResponseEntity<ErrorResponse> {
        log.info("{}", exception.printStackTrace())
        val errorResponse = ErrorResponse(
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exception = exception::class.simpleName,
            message = exception.localizedMessage ?: "",
            fieldErrors = emptyList()
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
