package com.jsilval.marvel.core.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.google.gson.JsonObject;
import com.jsilval.marvel.features.list.model.Marvel;

public class CustomJacksonConverter implements Converter<JsonObject, Marvel> {

    @Override
    public Marvel convert(JsonObject value) {
        return null;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
