package me.hinsinger.hinz.translation.language.impl;

import java.util.Objects;

import lombok.Getter;
import me.hinsinger.hinz.translation.language.Language;

public class SimpleLanguage implements Language {

	public static SimpleLanguage of(String code, String name) {
		return new SimpleLanguage(code, name);
	}

	@Getter private final String code;
	@Getter private final String name;
	
	private SimpleLanguage(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null || getClass() != obj.getClass()) return false;
		
		return Objects.equals(code, ((SimpleLanguage) obj).code) &&
		       Objects.equals(name, ((SimpleLanguage) obj).name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}
}
