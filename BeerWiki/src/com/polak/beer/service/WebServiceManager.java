package com.polak.beer.service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

public class WebServiceManager {
	
	private static final String TAG = "WebServiceManager";
	private static RestTemplate restTemplate;

	private RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		}
		return restTemplate;
	}

	protected <T> T getForEntity(String url, Class<T> entityClass) {
		Log.d(TAG, "URL: " + url);
		return getRestTemplate().getForObject(url, entityClass);
	}

}
