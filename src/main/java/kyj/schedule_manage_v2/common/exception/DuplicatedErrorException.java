package kyj.schedule_manage_v2.common.exception;

import kyj.schedule_manage_v2.common.exception.handler.ServiceErrorException;
import org.springframework.http.HttpStatus;

public class DuplicatedErrorException extends ServiceErrorException {
    public DuplicatedErrorException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
