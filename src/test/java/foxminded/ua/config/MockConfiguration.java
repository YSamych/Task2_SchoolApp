package foxminded.ua.config;

import foxminded.ua.app.InputProcessor;
import foxminded.ua.app.SchoolApp;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mock")
@Configuration
public class MockConfiguration {

    @Primary
    @Bean
    public InputProcessor inputProcessor() {
        return Mockito.mock(InputProcessor.class);
    }

    @Primary
    @Bean
    public SchoolApp schoolApp() {
        return Mockito.mock(SchoolApp.class);
    }
}
