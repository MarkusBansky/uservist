package com.markiian.benovskyi.auth.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.markiian.benovskyi.auth.properties.UservistProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebMvc
public class UvWebConfig implements WebMvcConfigurer {

    private final UservistProperties uservistProperties;

    public UvWebConfig(UservistProperties uservistProperties) {
        this.uservistProperties = uservistProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/auth/**")
                .allowedOrigins(uservistProperties.getAllowedOrigins())
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*");
        registry
                .addMapping("/api/**")
                .allowedOrigins(uservistProperties.getAllowedOrigins())
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*");
    }

    /**
     * A hook for extending or modifying the list of converters after it has been
     * configured. This may be useful for example to allow default converters to
     * be registered and then insert a custom converter through this method.
     *
     * @param converters the list of configured converters to extend.
     * @since 4.1.3
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> converterOptional = converters.stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();
        if (converterOptional.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter =
                    (AbstractJackson2HttpMessageConverter) converterOptional.get();
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
