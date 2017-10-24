package hello;

import com.gopivotal.manager.SessionFlushValve;
import com.gopivotal.manager.redis.RedisStore;
import org.apache.catalina.Context;
import org.apache.catalina.session.PersistentManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloApplication {

    @Value("${redis.session-store.port:32768}")
    private int redisPort;

    @Value("${redis.session-store.host:localhost}")
    private String redisHost;

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        System.out.println("creating embedded servlet container");
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addContextCustomizers(new RedisSessionContextCustomizer());
        tomcat.addContextValves(new SessionFlushValve());
        return tomcat;
    }

    class RedisSessionContextCustomizer implements TomcatContextCustomizer {

        @Override
        public void customize(Context context) {
            PersistentManager persistentManager = new PersistentManager();
            RedisStore redisStore = new RedisStore();

            redisStore.setHost(redisHost);
            redisStore.setPort(redisPort);

            persistentManager.setStore(redisStore);
            context.setManager(persistentManager);
        }
    }
}
