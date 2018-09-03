/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.bicycle.shop.model.Product;
import com.bicycle.shop.repository.ProductRepository;
import com.bicycle.shop.service.ProductService;

/**
 * <b>Description:</b> The class implementation for {@link ProductService}
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 01/09/2018
 */
@Service
public class ProductServiceImpl implements ProductService {

	/** The product repository. */
	private final ProductRepository repository;

	/**
	 * Instantiates a new product service.
	 *
	 * @param repository the product repository
	 */
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product save(Product product) {
		return repository.save(product);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public List<Product> findAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Product findById(String id) {
		return repository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void delete(Product product) {
		repository.delete(product);
	}

}
