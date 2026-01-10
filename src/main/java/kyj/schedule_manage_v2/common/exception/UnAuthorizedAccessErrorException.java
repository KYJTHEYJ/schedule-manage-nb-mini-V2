package kyj.schedule_manage_v2.common.exception;

import kyj.schedule_manage_v2.common.exception.handler.ServiceErrorException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedAccessErrorException extends ServiceErrorException {
    public UnAuthorizedAccessErrorException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
