package com.openthinks.notes.web.service;

import java.lang.reflect.Field;

import com.openthinks.libs.utilities.Checker;

public class ServicesUtils<T> {

	private T service;

	public T inject(String properties, Object propertiesValue) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = service.getClass().getDeclaredField(properties);
		Checker.require(field).notNull();
		field.setAccessible(true);
		field.set(service, propertiesValue);
		return service;
	}

	public T setSession(Object session) {
		try {
			return inject("session", session);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <E> ServicesUtils<E> build(E service) {
		ServicesUtils<E> utils = new ServicesUtils<E>();
		utils.service = service;
		return utils;
	}
}
