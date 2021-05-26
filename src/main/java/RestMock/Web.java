package RestMock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class Web {
    @Autowired
    @Qualifier("webScheduler")
    ScheduledExecutorService webScheduler;

    @Autowired
    Delay delay;

    @SneakyThrows(IOException.class)
    @PostMapping(value = "/internal/someapi", produces = "application/xml")
    public DeferredResult<ResponseEntity<String>> someapi(HttpServletRequest request, HttpServletResponse response) {
        DeferredResult<ResponseEntity<String>> result;
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info("requestBody = '{}'", requestBody);
        result = new Response1().waitAndResp(requestBody, delay.getDelay(), webScheduler);
        return result;
    }

    @GetMapping("/")
    public String home() {
        return "Hello";
    }
}
