package kyj.schedule_manage_v2.common.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceErrorException extends RuntimeException {
    private final HttpStatus status;

    public ServiceErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
