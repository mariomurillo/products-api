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
 * <b>Description:</b> The class for response to product
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 02/09/2018
 */
@AllArgsConstructor
@Getter
@Builder
public class ProductResponseDto {

	/** The id of the product. */
	@JsonProperty("id")
	private String id;

	/** The name of the product. */
	@JsonProperty("name")
	private String name;

	/**
	 * Instantiates a new product response dto.
	 */
	public ProductResponseDto() {
	}
}
