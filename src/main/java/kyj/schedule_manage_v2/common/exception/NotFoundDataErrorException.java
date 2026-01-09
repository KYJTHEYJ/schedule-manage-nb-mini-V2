package kyj.schedule_manage_v2.common.exception;

import kyj.schedule_manage_v2.common.exception.handler.ServiceErrorException;
import org.springframework.http.HttpStatus;

public class NotFoundDataErrorException extends ServiceErrorException {
    public NotFoundDataErrorException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
