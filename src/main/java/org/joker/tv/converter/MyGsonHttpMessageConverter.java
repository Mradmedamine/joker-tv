package org.joker.tv.converter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

public class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {
	public MyGsonHttpMessageConverter() {
		List<MediaType> types = Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON_UTF8,
		        new MediaType("application", "*+json", DEFAULT_CHARSET));
		super.setSupportedMediaTypes(types);
	}
}