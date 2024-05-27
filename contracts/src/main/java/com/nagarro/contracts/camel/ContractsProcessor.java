package com.nagarro.contracts.camel;

import java.util.List;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.contracts.model.Product;


@Component
public class ContractsProcessor implements Processor {

    private final Logger logger = LoggerFactory.getLogger(ContractsProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            List<Product> products = exchange.getIn().getBody(List.class);

            for (Product product : products) {
                product.setContractId(UUID.randomUUID().toString());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String processedData = objectMapper.writeValueAsString(products);

            logger.info("Final JSON after processing: {}", processedData);

            exchange.getIn().setBody(processedData);
        } catch (Exception e) {
            throw new RuntimeException("Error processing contract data", e);
        }
    }
}
