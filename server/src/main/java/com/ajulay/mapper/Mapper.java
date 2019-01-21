package com.ajulay.mapper;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Mapper {

    public <T> String createJson(T data) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(baos);
        jsonGenerator.writeObject(data);
        System.out.println();
        jsonGenerator.close();

        return new String(baos.toByteArray());
    }
}
