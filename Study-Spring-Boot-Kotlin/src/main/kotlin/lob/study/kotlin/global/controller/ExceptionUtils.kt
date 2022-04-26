package lob.study.kotlin.global.controller


data class FieldError(
    val field: String,
    val errorCode: String?
)

data class ErrorResponse(
    val httpStatus: Int,
    val exception: String?,
    val message: String,
    val fieldErrors: List<FieldError>?
) {
    constructor(httpStatus: Int, exception: String?, message: String) : this(
        httpStatus = httpStatus,
        exception = exception ?: "",
        message = message,
        fieldErrors = emptyList()
    )
}

