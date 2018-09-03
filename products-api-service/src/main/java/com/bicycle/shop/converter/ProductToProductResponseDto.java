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

import com.bicycle.shop.dto.ProductRequestDto;
import com.bicycle.shop.dto.ProductResponseDto;
import com.bicycle.shop.model.Product;

/**
 * <b>Description:</b> The class for convert {@link ProductRequestDto} to {@link Product}
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 02/09/2018
 */
public class ProductToProductResponseDto implements Converter<Product, ProductResponseDto> {

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public ProductResponseDto convert(final Product source) {
		return ProductResponseDto.builder()
				.id(source.getId())
				.name(source.getName())
				.build();
	}

}
