/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.bicycle.shop.model.Product;
import com.bicycle.shop.repository.ProductRepository;
import com.bicycle.shop.service.ProductService;

/**
 * <b>Description:</b> The test class for {@link ProductServiceImpl}
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 01/09/2018
 */
public class ProductServiceImplMockTest {

	/** The product service. */
	private ProductService service;
	
	/** The product repository. */
	private ProductRepository repository;

	/**
	 * The method that runs before every test.
	 */
	@Before
	public void setUp() {

		repository = mock(ProductRepository.class);

		service = new ProductServiceImpl(repository);
	}

	/**
	 * Save product success test.
	 */
	@Test
	public void saveProductSuccessTest() {

		reset(repository);

		final Product productSaved = service.save(Product.builder().build());

		verify(repository).save(Product.builder().build());

		assertNotNull(productSaved);
	}
}
