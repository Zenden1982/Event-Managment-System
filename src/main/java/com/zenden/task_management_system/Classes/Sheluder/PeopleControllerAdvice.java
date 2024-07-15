package com.zenden.task_management_system.Classes.Sheluder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zenden.task_management_system.Classes.DTO.LocationDTO;

@ControllerAdvice
public class PeopleControllerAdvice implements ResponseBodyAdvice<LocationDTO> {

    @Autowired
    GetPeopleFromFile GetPeopleFromFile;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {return LocationDTO.class.isAssignableFrom(returnType.getParameterType());}
        catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'supports'");
        }
    }

    @Override
    @Nullable
    public LocationDTO beforeBodyWrite(@Nullable LocationDTO body, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        double people = GetPeopleFromFile.getPeople();

        if (body != null) {
            body.setCapacity((int)(body.getCapacity() + people));
            return body;
        }
        else throw new UnsupportedOperationException("Unimplemented method 'beforeBodyWrite'");
    }
    
}
