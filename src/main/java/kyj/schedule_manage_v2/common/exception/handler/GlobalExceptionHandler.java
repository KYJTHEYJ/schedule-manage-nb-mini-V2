package kyj.schedule_manage_v2.common.exception.handler;

import kyj.schedule_manage_v2.common.exception.dto.ErrorExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorExceptionResponse(e.getAllErrors().getFirst().getDefaultMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorExceptionResponse> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.error("데이터 등록 실패 발생 : ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorExceptionResponse("데이터 등록에 실패하였습니다"));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorExceptionResponse> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.error("주소 값 변환 실패 발생 : ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorExceptionResponse("잘못된 요청입니다"));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ErrorExceptionResponse> NoResourceFoundException(NoResourceFoundException e) {
        log.error("자원 찾기 실패 : ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorExceptionResponse(e.getResourcePath() + " 에 따른 자원을 찾지 못했습니다"));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorExceptionResponse> UnControllerErrorException(Exception e) {
        log.error("서버 에러 발생 : ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorExceptionResponse("서버 에러가 발생하였습니다"));
    }
}
