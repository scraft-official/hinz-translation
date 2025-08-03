package me.hinsinger.hinz.translation.impl;

import lombok.Getter;
import me.hinsinger.hinz.translation.Translation;

public class SimpleTranslation implements Translation {

	public static SimpleTranslation of(String namespace, String identifier, String fallback) {
		return new SimpleTranslation(namespace, identifier, fallback);
	}
	
	@Getter private String namespace;
	@Getter private String identifier;
	@Getter private String fallback;

	public SimpleTranslation(String namespace, String identifier, String fallback) {
		this.namespace = namespace;
		this.identifier = identifier;
		this.fallback = fallback;
	}
}
