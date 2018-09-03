/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.bicycle.shop.model.Product;

/**
 * <b>Description:</b> The repository for operations with DB for the Product
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 28/08/2018
 */
public interface ProductRepository extends CrudRepository<Product, String> {

}
