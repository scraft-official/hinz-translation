package me.hinsinger.hinz.translation.placeholder.impl;

import java.util.Objects;

import lombok.Getter;
import me.hinsinger.hinz.translation.placeholder.Placeholder;

public class StaticPlaceholder implements Placeholder {

	public static StaticPlaceholder of(String name, Object value) {
		return new StaticPlaceholder(name, String.valueOf(value));
	}
	
	public static StaticPlaceholder of(String name, String value) {
		return new StaticPlaceholder(name, value);
	}
	
	@Getter private String name;
	@Getter private String value;
	
	private StaticPlaceholder(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null || !(obj instanceof Placeholder)) return false;
		
		return Objects.equals(name, ((Placeholder) obj).getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
