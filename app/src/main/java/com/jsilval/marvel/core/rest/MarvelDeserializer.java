package com.jsilval.marvel.core.rest;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jsilval.marvel.features.list.model.Marvel;

import java.lang.reflect.Type;
import java.util.List;

public class MarvelDeserializer implements JsonDeserializer<List<Marvel>> {

    @Override
    public List<Marvel> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d(getClass().getSimpleName(), "deserialize: ");

        return null;
    }
}
