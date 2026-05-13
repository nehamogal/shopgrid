package com.shopgrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching; 

@SpringBootApplication
@EnableCaching
public class ShopgridApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopgridApplication.class, args);
	}

}
