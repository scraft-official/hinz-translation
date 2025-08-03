package me.hinsinger.hinz.translation.placeholder.impl;

import java.util.Objects;
import java.util.function.Supplier;

import lombok.Getter;
import me.hinsinger.hinz.translation.placeholder.Placeholder;

public class DynamicPlaceholder implements Placeholder {
	
	public static DynamicPlaceholder of(String name, Object value) {
		return of(name, String.valueOf(value));
	}
	
	public static DynamicPlaceholder of(String name, String value) {
		return of(name, () -> value);
	}
	
	public static DynamicPlaceholder of(String name, Supplier<Object> supplier) {
		return new DynamicPlaceholder(name, () -> String.valueOf(supplier.get()));
	}
	
	@Getter private String name;
	@Getter private Supplier<String> supplier;
	
	private DynamicPlaceholder(String name, Supplier<String> supplier) {
		this.name = name;
		this.supplier = supplier;
	}
	
	@Override
	public String getValue() {
		return String.valueOf(supplier.get());
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
