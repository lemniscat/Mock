package RestMock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@Slf4j
public class Beans {

    @Bean(name = "webScheduler")
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(100);
    }
}
