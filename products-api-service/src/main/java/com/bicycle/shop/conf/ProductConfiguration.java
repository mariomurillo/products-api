/**
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 * 
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 */
package com.bicycle.shop.conf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.bicycle.shop.converter.ProductRequestDtoToProduct;
import com.bicycle.shop.converter.ProductToProductDto;
import com.bicycle.shop.converter.ProductToProductResponseDto;

/**
 * <b>Description:</b> The class for the configuration of products
 * 
 * @author <a href="mailto:mardres@gmail.com">Mario Andres Murillo</a>
 * @date 30/08/2018
 */
@Configuration
public class ProductConfiguration {

	/**
	 * Gets the conversion service.
	 *
	 * @return the conversion service
	 */
	@SuppressWarnings("rawtypes")
	@Bean(name = "conversionService")
	public ConversionService getConversionService() {

		final Set<Converter> converters = new HashSet<>();
		converters.add(new ProductRequestDtoToProduct());
		converters.add(new ProductToProductResponseDto());
		converters.add(new ProductToProductDto());

		final ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(converters);

		bean.afterPropertiesSet();

		return bean.getObject();
	}
}
