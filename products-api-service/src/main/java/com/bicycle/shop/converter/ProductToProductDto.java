/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.converter;

import org.springframework.core.convert.converter.Converter;

import com.bicycle.shop.dto.ProductDto;
import com.bicycle.shop.model.Product;

/**
 * <b>Description:</b> The class for convert {@link Produc} to {@link ProductDto}
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 02/09/2018
 */
public class ProductToProductDto implements Converter<Product, ProductDto> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public ProductDto convert(Product source) {
		return ProductDto.builder()
				.name(source.getName())
				.price(source.getPrice().toPlainString())
				.description(source.getDescription())
				.image(source.getImage().toString())
				.build();
	}

}
