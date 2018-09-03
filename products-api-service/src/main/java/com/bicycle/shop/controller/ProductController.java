/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bicycle.shop.dto.ProductDto;
import com.bicycle.shop.dto.ProductRequestDto;
import com.bicycle.shop.dto.ProductResponseDto;
import com.bicycle.shop.model.Product;
import com.bicycle.shop.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * <b>Description:</b> The class controller for products
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 29/08/2018
 */
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

	/** The Constant for message product deleted. */
	private static final String MESSAGE_PRODUCT_DELETED = "Product deleted";

	/** The product service. */
	private final ProductService service;

	/** The conversion service. */
	private final ConversionService conversionService;

	/**
	 * Instantiates a new product controller.
	 *
	 * @param service the product service
	 */
	public ProductController(final ProductService service, final ConversionService conversionService) {
		this.service = service;
		this.conversionService = conversionService;
	}

	/**
	 * Save a product.
	 *
	 * @param product the product to save
	 */
	@PostMapping
	public ResponseEntity<ProductResponseDto> save(@RequestBody final ProductRequestDto product) {
		try {
			return new ResponseEntity<>(
					conversionService.convert(service.save(getProduct(product)), ProductResponseDto.class), OK);

		} catch (final Exception e) {

			log.error(e.getMessage(), e);

			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find all products.
	 *
	 * @return the products found
	 */
	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> findAll() {

		try {
			final List<ProductResponseDto> response = new ArrayList<>();

			service.findAll().stream().forEach(p -> {
				response.add(conversionService.convert(p, ProductResponseDto.class));
				});

			return new ResponseEntity<List<ProductResponseDto>>(response, OK);

		} catch (final Exception e) {

			log.error(e.getMessage(), e);

			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Find product by id.
	 *
	 * @param id the id of the product to find
	 * @return the product found
	 */
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable final String id) {
		try {
			return new ResponseEntity<ProductDto>(
					conversionService.convert(service.findById(id), ProductDto.class), OK);

		} catch (final Exception e) {

			log.error(e.getMessage(), e);

			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete product by id.
	 *
	 * @param id the id of the product to delete
	 * @return the message response
	 */
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<String> delete(@PathVariable final String id) {

		try {
			service.delete(service.findById(id));

			return new ResponseEntity<String>(MESSAGE_PRODUCT_DELETED, OK);

		} catch (final Exception e) {

			log.error(e.getMessage(), e);

			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the product.
	 *
	 * @param product the product request dto
	 * @return the product
	 */
	private Product getProduct(final ProductRequestDto product) {
		return conversionService.convert(product, Product.class);
	}
	
}
