package com.example.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class MessageConvertor extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter< ?>> converters) {
		System.err.println("Configuring Messaage Convertor... ");
	    List<MediaType> mediaType = new ArrayList<>();
	    mediaType.add(MediaType.TEXT_XML);

	    MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
	    
	    xmlConverter.setSupportedMediaTypes(mediaType);

	    XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
	    xmlConverter.setMarshaller(xstreamMarshaller);
	    xmlConverter.setUnmarshaller(xstreamMarshaller);

	    converters.add(xmlConverter);
	}
}
