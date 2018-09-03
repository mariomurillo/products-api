/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;

import com.bicycle.shop.dto.ProductDto;
import com.bicycle.shop.dto.ProductRequestDto;
import com.bicycle.shop.dto.ProductResponseDto;
import com.bicycle.shop.model.Product;
import com.bicycle.shop.service.ProductService;

/**
 * <b>Description:</b> The test class for {@link ProductController}
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 29/08/2018
 */
public class ProductControllerMockTest {

	/** The Constant for message product deleted. */
	private static final String MESSAGE_PRODUCT_DELETED = "Product deleted";

	/** The Constant for value product id. */
	private static final String VALUE_PRODUCT_ID = "1";

	/** The product controller. */
	private ProductController controller;

	/** The product service. */
	private ProductService productService;

	/** The conversion service. */
	private ConversionService conversionService;

	/**
	 * The method that runs before every test.
	 */
	@Before
	public void setUp() {

		conversionService = mock(ConversionService.class);
		productService = mock(ProductService.class);

		controller = new ProductController(productService, conversionService);
	}

	/**
	 * The test case for save product success.
	 */
	@Test
	public void saveProductSuccessTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		final ProductRequestDto productRequest = getProductRequest();

		when(conversionService.convert(productRequest, Product.class)).thenReturn(product);
		when(conversionService.convert(product, ProductResponseDto.class)).thenReturn(getProductResponse());
		when(productService.save(product)).thenReturn(product);

		final ResponseEntity<ProductResponseDto> response = controller.save(productRequest);

		verify(conversionService).convert(productRequest, Product.class);
		verify(conversionService).convert(product, ProductResponseDto.class);
		verify(productService).save(product);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(OK, response.getStatusCode());
	}

	/**
	 * The test case for save product with conversion exception.
	 */
	@Test
	public void saveProductWithConversionExceptionTest() {

		reset(productService, conversionService);

		final ProductRequestDto productRequest = getProductRequest();

		when(conversionService.convert(productRequest, Product.class)).thenThrow(ConversionFailedException.class);

		final ResponseEntity<ProductResponseDto> response = controller.save(productRequest);

		verify(conversionService).convert(productRequest, Product.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for save product with illegal argument exception.
	 */
	@Test
	public void saveProductWithIllegalArgumentExceptionTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		final ProductRequestDto productRequest = getProductRequest();

		when(conversionService.convert(productRequest, Product.class)).thenReturn(product);
		when(conversionService.convert(product, ProductResponseDto.class)).thenThrow(IllegalArgumentException.class);
		when(productService.save(product)).thenReturn(product);

		final ResponseEntity<ProductResponseDto> response = controller.save(productRequest);

		verify(conversionService).convert(productRequest, Product.class);
		verify(conversionService).convert(product, ProductResponseDto.class);
		verify(productService).save(product);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for save product with null exception.
	 */
	@Test
	public void saveProductWithNullTest() {

		reset(productService, conversionService);

		final ProductRequestDto productRequest = getProductRequest();

		when(conversionService.convert(productRequest, Product.class)).thenReturn(null);
		when(productService.save(null)).thenThrow(IllegalArgumentException.class);

		final ResponseEntity<ProductResponseDto> response = controller.save(productRequest);

		verify(conversionService).convert(productRequest, Product.class);
		verify(productService).save(null);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test dcase for find all products.
	 */
	@Test
	public void findAllSuccessTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findAll()).thenReturn(Arrays.asList(product));
		when(conversionService.convert(product, ProductResponseDto.class)).thenReturn(getProductResponse());

		final ResponseEntity<List<ProductResponseDto>> response = controller.findAll();

		verify(productService).findAll();
		verify(conversionService).convert(product, ProductResponseDto.class);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(OK, response.getStatusCode());
		assertFalse(response.getBody().isEmpty());
	}

	/**
	 * The test case for find all product without results.
	 */
	@Test
	public void findAllProductWithoutResultsTest() {

		reset(productService, conversionService);

		when(productService.findAll()).thenReturn(Collections.emptyList());

		final ResponseEntity<List<ProductResponseDto>> response = controller.findAll();

		verify(productService).findAll();

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertNotNull(response.getStatusCode());
		assertEquals(OK, response.getStatusCode());
		assertTrue(response.getBody().isEmpty());
	}

	/**
	 * The test case for find all product with conversion exception.
	 */
	@Test
	public void findAllProductWithConversionExceptionTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findAll()).thenReturn(Arrays.asList(product));
		when(conversionService.convert(product, ProductResponseDto.class)).thenThrow(ConversionFailedException.class);

		final ResponseEntity<List<ProductResponseDto>> response = controller.findAll();

		verify(productService).findAll();
		verify(conversionService).convert(product, ProductResponseDto.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for find all product with illegal argument exception.
	 */
	@Test
	public void findAllProductWithIllegalArgumentExceptionTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findAll()).thenReturn(Arrays.asList(product));
		when(conversionService.convert(product, ProductResponseDto.class)).thenThrow(IllegalArgumentException.class);

		final ResponseEntity<List<ProductResponseDto>> response = controller.findAll();

		verify(productService).findAll();
		verify(conversionService).convert(product, ProductResponseDto.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for find by id success.
	 */
	@Test
	public void findByIdSuccessTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findById(VALUE_PRODUCT_ID)).thenReturn(product);
		when(conversionService.convert(product, ProductDto.class)).thenReturn(getProductDto());

		final ResponseEntity<ProductDto> response = controller.findById(VALUE_PRODUCT_ID);

		verify(productService).findById(VALUE_PRODUCT_ID);
		verify(conversionService).convert(product, ProductDto.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(OK, response.getStatusCode());
	}

	/**
	 * The test case for find by id with null id.
	 */
	@Test
	public void findByIdWithNullIdTest() {

		reset(productService, conversionService);

		when(productService.findById(null)).thenThrow(IllegalArgumentException.class);

		final ResponseEntity<ProductDto> response = controller.findById(null);

		verify(productService).findById(null);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for find by id with conversion exception.
	 */
	@Test
	public void findByIdWithConversionExceptionTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findById(VALUE_PRODUCT_ID)).thenReturn(product);
		when(conversionService.convert(product, ProductDto.class)).thenThrow(ConversionFailedException.class);

		final ResponseEntity<ProductDto> response = controller.findById(VALUE_PRODUCT_ID);

		verify(productService).findById(VALUE_PRODUCT_ID);
		verify(conversionService).convert(product, ProductDto.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for find by id with illegal argument exception.
	 */
	@Test
	public void findByIdWithIllegalArgumentExceptionTest() {

		reset(productService, conversionService);

		final Product product = getProduct();

		when(productService.findById(VALUE_PRODUCT_ID)).thenReturn(product);
		when(conversionService.convert(product, ProductDto.class)).thenThrow(IllegalArgumentException.class);

		final ResponseEntity<ProductDto> response = controller.findById(VALUE_PRODUCT_ID);

		verify(productService).findById(VALUE_PRODUCT_ID);
		verify(conversionService).convert(product, ProductDto.class);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * The test case for delete success.
	 */
	@Test
	public void deleteSuccessTest() {

		reset(productService);

		final Product product = getProduct();

		when(productService.findById(VALUE_PRODUCT_ID)).thenReturn(product);

		final ResponseEntity<String> response = controller.delete(VALUE_PRODUCT_ID);

		verify(productService).findById(VALUE_PRODUCT_ID);
		verify(productService).delete(product);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(OK, response.getStatusCode());
		assertEquals(MESSAGE_PRODUCT_DELETED, response.getBody());
	}

	/**
	 * The test case for delete with null.
	 */
	@Test
	public void deleteWithNullTest() {

		reset(productService);

		when(productService.findById(null)).thenThrow(IllegalArgumentException.class);

		final ResponseEntity<String> response = controller.delete(null);

		verify(productService).findById(null);

		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * Gets the product dto.
	 *
	 * @return the product dto
	 */
	private ProductDto getProductDto() {
		return ProductDto.builder().build();
	}

	/**
	 * Gets the product response.
	 *
	 * @return the product response dto
	 */
	private ProductResponseDto getProductResponse() {
		return ProductResponseDto.builder().build();
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	private Product getProduct() {
		return Product.builder().build();
	}

	/**
	 * Gets the product request.
	 *
	 * @return the product request dto
	 */
	private ProductRequestDto getProductRequest() {
		return ProductRequestDto.builder().build();
	}
}
