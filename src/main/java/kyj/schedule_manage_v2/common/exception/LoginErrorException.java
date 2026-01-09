package kyj.schedule_manage_v2.common.exception;

import kyj.schedule_manage_v2.common.exception.handler.ServiceErrorException;
import org.springframework.http.HttpStatus;

public class LoginErrorException extends ServiceErrorException {
    public LoginErrorException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
