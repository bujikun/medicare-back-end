package com.bujikun.fsd.capstone.eHealthcare.config.http.converter;


import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.IOException;

/**
 * An implementation of HttpMessageConverter used to convert
 * a body of a PATCH request/response into a JsonPatch object
 *
 * @author  Newton Bujiku
 * @since 2023
 */

public class JsonPatchHttpMessageConverter  extends AbstractHttpMessageConverter<JsonPatch> {
    private static final String APPLICATION_JSON_PATCH_JSON = "application/json-patch+json";

    public JsonPatchHttpMessageConverter() {
        super(MediaType.valueOf(APPLICATION_JSON_PATCH_JSON));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return JsonPatch.class.isAssignableFrom(clazz);
    }

    @Override
    protected JsonPatch readInternal(Class<? extends JsonPatch> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        //create a reader to read input message body as Json
        //create a patch from the array in the body
        try(JsonReader jsonReader = Json.createReader(inputMessage.getBody())){
            return Json.createPatch(jsonReader.readArray());

        }catch (Exception e){
            throw new HttpMessageNotReadableException(e.getMessage(),inputMessage);
        }
    }

    //this method is unlikely to used as we'll unlikely need to
    //convert JsonPatch to HTTP Response
    @Override
    protected void writeInternal(JsonPatch jsonPatch, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try(JsonWriter jsonWriter = Json.createWriter(outputMessage.getBody())){
            jsonWriter.write(jsonPatch.toJsonArray());
        }catch (Exception e){
            throw new HttpMessageNotWritableException(e.getMessage(),e);

        }

    }
}
