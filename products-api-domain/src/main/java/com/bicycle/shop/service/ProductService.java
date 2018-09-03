/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.service;

import java.util.List;

import com.bicycle.shop.model.Product;

/**
 * <b>Description:</b> The class service for products
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 29/08/2018
 */
public interface ProductService {

	/**
	 * Save a product.
	 *
	 * @param product the product
	 * @return the product saved
	 */
	Product save(Product product);

	/**
	 * Find all products.
	 *
	 * @return the list with products found
	 */
	List<Product> findAll();

	/**
	 * Find product by id.
	 *
	 * @param id the id of the product to find
	 * @return the product found
	 */
	Product findById(String id);

	/**
	 * Delete product.
	 *
	 * @param product the product to delete
	 */
	void delete(Product product);
}
