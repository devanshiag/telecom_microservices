package com.nagarro.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.shoppingcart.model.Product;

@RestController
@RequestMapping("/cart/v1")
public class ShoppingCartController {
	
	private final WebClient contractsWebClient;

    @Autowired
    public ShoppingCartController(WebClient.Builder webClientBuilder) {
        this.contractsWebClient = webClientBuilder.baseUrl("http://localhost:9095").build();
    }
	
	@PostMapping("/submit")
	public String submitCart(@RequestBody List<Product> products) {
      System.out.println("Received cart submission request with products: " + products.toString());
      contractsWebClient.post()
      .uri("/contracts/v1/submit")
      .bodyValue(products)
      .retrieve()
      .bodyToMono(String.class)
      .subscribe(response -> {
          System.out.println("Response from Contracts service: " + response);
      });
      return "Product added to the shopping cart";
	}
}
