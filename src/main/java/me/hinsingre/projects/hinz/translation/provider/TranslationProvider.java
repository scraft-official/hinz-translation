package me.hinsingre.projects.hinz.translation.provider;

public interface TranslationProvider {
	public boolean contains(String namespace, String identifier, String language);
	public String translate(String namespace, String identifier, String language);
	public void init();
}
