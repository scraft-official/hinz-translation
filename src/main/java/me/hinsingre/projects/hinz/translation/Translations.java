package me.hinsingre.projects.hinz.translation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.hinsingre.projects.hinz.translation.language.Language;
import me.hinsingre.projects.hinz.translation.provider.TranslationProvider;

public class Translations {
	public List<TranslationProvider> providers = new ArrayList<>();
	
	public static void init(TranslationProvider... providers) {
		getInstance().providers = Arrays.asList(providers);
	}
	
	public static String translate(Translation translation, Language language) {
		String namespace = translation.getNamespace();
		String identifier = translation.getIdentifier();
		String langcode = language.getCode();
		
		for(TranslationProvider provider : getInstance().providers) {
			if(!provider.contains(namespace, identifier, langcode)) continue;
			
			return provider.translate(namespace, identifier, langcode);
		}
		
		return translation.getDefault();
	}
	
	public static List<String> translate(List<Translation> translations, Language language) {
		return translations.stream()
				.map(t -> translate(t, language))
				.collect(Collectors.toList());
	}
	
	public static Map<Language, String> translate(Translation translation, List<Language> languages) {
		return languages.stream().collect(Collectors.toMap(l -> l, l -> translate(translation, l)));
	}
	
	public static Translations getInstance() {
		return Holder.INSTANCE;
	}
	
	public static class Holder {
		private static final Translations INSTANCE = new Translations();
	}
}
