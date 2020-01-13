package com.hbt.semillero.entidad;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

	protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    	 final String date = parser.getText();
    	 LocalDate dt = LocalDate.parse(date,formatter); 
    	 
    	 return dt;
        //return LocalDate.parse(parser.readValueAs(String.class));
    }
}