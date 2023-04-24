package org.edu.unidep.core.modelmapper;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import org.modelmapper.ModelMapper;

import io.quarkus.arc.DefaultBean;

@Dependent
public class ModelMapperConfig {

    @Produces
    @DefaultBean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		return modelMapper;
	}
}
