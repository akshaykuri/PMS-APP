package com.nipppon.p2p.web.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration{
	@Bean("modelMapper")
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}