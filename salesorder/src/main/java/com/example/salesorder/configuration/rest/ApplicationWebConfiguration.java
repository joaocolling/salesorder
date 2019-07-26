/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.rest;

/**
 *
 * @author joao
 */
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApplicationWebConfiguration implements WebMvcConfigurer {

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        converters.add(new StringHttpMessageConverter());
    }
    
    @Bean
    public FilterRegistrationBean squigglyRequestFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new SquigglyRequestFilter());
        filter.setOrder(1);
        return filter;
    }

    @Bean
    public ObjectMapper configureObjectMapper() {
        Jackson2ObjectMapperBuilder builder = builderSerializable();
        ObjectMapper objectMapper = builder.build();
        
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider() {
            @Override
            protected String customizeFilter(String filter, HttpServletRequest request, Class beanClass) {
                return filter;
            }
        });

        return objectMapper;

    }

    private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter(configureObjectMapper());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(configureObjectMapper());
        return converter;
    }

    private Jackson2ObjectMapperBuilder builderSerializable() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        builder.serializerByType(LocalDate.class, serializarLocalDate());
        builder.deserializerByType(LocalDate.class, deserializarLocalDate());
        builder.serializerByType(LocalDateTime.class, serializarLocalDatetime());
        builder.deserializerByType(LocalDateTime.class, deserializarLocalDatetime());
        return builder;
    }

    private JsonDeserializer<LocalDateTime> deserializarLocalDatetime() {
        return new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
                String str = jsonParser.getText();
                if (str == null || str.trim().isEmpty()) {
                    return null;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                DateTimeFormatter formatterDate = DateTimeFormatter.ISO_DATE;

                LocalDateTime localDateTime = null;
                try {
                    localDateTime = LocalDateTime.parse(str, formatter);
                } catch (DateTimeParseException ex) {
                    try {
                        localDateTime = LocalDate.parse(str, formatterDate).atStartOfDay();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return localDateTime;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    return localDateTime;
                }

                return localDateTime;
            }
        };
    }

    private JsonSerializer<LocalDateTime> serializarLocalDatetime() {
        return new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {
                if (localDateTime != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                    String str = localDateTime.format(formatter);

                    jsonGenerator.writeString(str);
                } else {
                    jsonGenerator.writeNull();
                }
            }
        };
    }

    private JsonDeserializer<LocalDate> deserializarLocalDate() {
        return new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
                String str = jsonParser.getText();
                if (str == null || str.trim().isEmpty()) {
                    return null;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

                LocalDate localDate = null;
                try {
                    localDate = LocalDate.parse(str, formatter);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return localDate;
                }

                return localDate;
            }
        };
    }

    private JsonSerializer<LocalDate> serializarLocalDate() {
        return new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider sp) throws IOException, JsonProcessingException {

                if (localDate != null) {
                    String str = localDate.toString();

                    jsonGenerator.writeString(str);
                } else {
                    jsonGenerator.writeNull();
                }
            }
        };
    }
}
