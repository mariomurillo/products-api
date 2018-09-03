/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * <b>Description:</b> The class controller for products
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 02/09/2018
 */
@AllArgsConstructor
@Getter
@Builder
public class ProductDto {

	/** The name of the product. */
	@JsonProperty("name")
	private String name;

	/** The price of the product. */
	@JsonProperty("price")
	private String price;

	/** The description of the product. */
	@JsonProperty("description")
	private String description;

	/** The URL image. */
	@JsonProperty("image")
	private String image;
}
