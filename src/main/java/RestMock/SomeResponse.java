package RestMock;


import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class SomeResponse {
    protected abstract String getRespBody(String request);

    public DeferredResult<ResponseEntity<String>> waitAndResp(String request, int delay, ScheduledExecutorService scheduler) {
        DeferredResult<ResponseEntity<String>> response = new DeferredResult<>();
        scheduler.schedule(() -> {
            response.setResult(ResponseEntity.ok(getRespBody(request)));
        }, delay, TimeUnit.MILLISECONDS);
        return response;
    }
}
