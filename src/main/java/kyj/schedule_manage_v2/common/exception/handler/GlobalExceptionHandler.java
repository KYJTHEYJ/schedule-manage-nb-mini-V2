package kyj.schedule_manage_v2.common.exception.handler;

import kyj.schedule_manage_v2.common.exception.dto.ErrorExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServiceErrorException.class)
    public ResponseEntity<ErrorExceptionResponse> ServiceExceptionHandler(ServiceErrorException e) {
        log.error("서비스 에러 발생 : ", e);
        return ResponseEntity.status(e.getStatus()).body(new ErrorExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorExceptionResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("데이터 유효성 에러 발생 : ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorExceptionResponse> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.error("데이터 등록 실패 발생 : ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorExceptionResponse> UnControllerErrorException(Exception e) {
        log.error("서버 에러 발생 : ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorExceptionResponse(e.getMessage()));
    }
}
