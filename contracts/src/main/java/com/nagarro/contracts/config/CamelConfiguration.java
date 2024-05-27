package com.nagarro.contracts.config;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.contracts.camel.ContractsProcessor;

@Configuration
public class CamelConfiguration extends RouteBuilder {

    @Autowired
    private ContractsProcessor contractsProcessor;
    
    @Value("${customer.registration.url}")
    private String customerRegistrationUrl;

    @Override
    public void configure() throws Exception {
  
        from("direct:processContracts")
        .routeId("processContractsRoute")
        .process(contractsProcessor)
        .setHeader(Exchange.HTTP_METHOD, constant("PATCH"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setHeader(Exchange.HTTP_PATH, constant("/api/v1/store/update-contracts"))
        .to(customerRegistrationUrl)  
        .log("Response from Customer Registration service: ${body}");

    }
    
    

    @Bean
    public Processor myProcessor() {
        return new ContractsProcessor();
    }
}
