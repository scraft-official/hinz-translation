package me.hinsinger.hinz.translation.impl;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import me.hinsinger.hinz.common.unique.set.UniqueSet;
import me.hinsinger.hinz.translation.Translation;
import me.hinsinger.hinz.translation.placeholder.Placeholder;

public class ComplexTranslation implements Translation {

	public static ComplexTranslation of(String namespace, String identifier, String fallback) {
		return new ComplexTranslation(namespace, identifier, fallback);
	}

	private Set<Placeholder> placeholders = new UniqueSet<>();
	
	@Getter private String namespace;
	@Getter private String identifier;
	@Getter private String fallback;
	
	public ComplexTranslation(String namespace, String identifier, String fallback) {
		this.namespace = namespace;
		this.identifier = identifier;
		this.fallback = fallback;
	}
	
	public void addPlaceholder(Placeholder placeholder) {
		this.placeholders.add(placeholder);
	}
	
	public void removePlaceholder(Placeholder placeholder) {
		this.placeholders.add(placeholder);
	}

	public Set<Placeholder> getPlaceholders() {
		return new HashSet<>(placeholders);
	}
}
