package com.example.salesorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SalesOrderApplication {

//    @Bean
//    public FilterRegistrationBean squigglyRequestFilter() {
//        FilterRegistrationBean filter = new FilterRegistrationBean();
//        filter.setFilter(new SquigglyRequestFilter());
//        filter.setOrder(1);
//        return filter;
//    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SalesOrderApplication.class, args);
        
//        Iterable<ObjectMapper> objectMappers = context.getBeansOfType(ObjectMapper.class)
//                .values();
//
//        Squiggly.init(objectMappers, new RequestSquigglyContextProvider() {
//            @Override
//            protected String customizeFilter(String filter, HttpServletRequest request, Class beanClass) {
////                // OPTIONAL: automatically wrap filter expressions in items{} when the object is a ListResponse
////                if (filter != null && ListResponse.class.isAssignableFrom(beanClass)) {
////                    filter = "items[" + filter + "]";
////                }
//                return filter;
//            }
//        });
//
//        ObjectMapper objectMapper = Iterables.getFirst(objectMappers, null);
//
//        // Enable Squiggly for Jackson message converter
//        if (objectMapper != null) {
//            for (MappingJackson2HttpMessageConverter converter : context.getBeansOfType(MappingJackson2HttpMessageConverter.class).values()) {
//                converter.setObjectMapper(objectMapper);
//            }
//        }
    }
}
