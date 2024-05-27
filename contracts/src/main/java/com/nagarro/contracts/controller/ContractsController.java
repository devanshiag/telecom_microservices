package com.nagarro.contracts.controller;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.contracts.model.Product;


@RestController
@RequestMapping("/contracts/v1")
public class ContractsController {
	
	 @Autowired
	 private ProducerTemplate producerTemplate;	

    @PostMapping("/submit")
    public String submitContracts(@RequestBody List<Product> products) {
        System.out.println("Received contract submission request with products: " + products.toString());

        producerTemplate.sendBody("direct:processContracts", products);

        return "Contracts processed successfully";
    }
}