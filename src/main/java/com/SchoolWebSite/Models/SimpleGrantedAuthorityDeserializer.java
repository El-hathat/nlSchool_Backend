package com.SchoolWebSite.Models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class SimpleGrantedAuthorityDeserializer extends JsonDeserializer<SimpleGrantedAuthority> {

    @Override
    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        String authority = jsonParser.getText();
        return new SimpleGrantedAuthority(authority);
    }
}
