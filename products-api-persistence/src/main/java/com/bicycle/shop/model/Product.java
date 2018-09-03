/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.model;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class represents an item of the bicycle
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 28/08/2018
 */
@RequiredArgsConstructor
@Getter
@Builder
@Document
public class Product {

	@Id
	/** The identify of the product. */
	private final String id;

	/** The name of the product. */
	private final String name;

	/** The price of the product. */
	private final BigDecimal price;

	/** The description of the product. */
	private final String description;

	/** The URL image. */
	private final URI image;
}
