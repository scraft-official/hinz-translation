package me.hinsinger.hinz.translation.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import me.hinsinger.hinz.common.multi.key.map.impl.BiMultiKeyMap;
import me.hinsinger.hinz.common.multi.key.map.impl.TriMultiKeyMap;
import me.hinsinger.hinz.translation.Translation;
import me.hinsinger.hinz.translation.language.Language;
import me.hinsinger.hinz.translation.provider.TranslationProvider;

public class MemoryTranslationProvider implements TranslationProvider {

	private final TriMultiKeyMap<Language, String, String, String> translations = new TriMultiKeyMap<>();

	public void add(Language language, String namespace, String identifier, String translation) {
		translations.put(language, namespace, identifier, translation);
	}

	@Override
	public void init() {}

	@Override
	public String translate(Translation translation, Language language) {
		Optional<String> value = this.translations.getOptional(language, translation.getNamespace(), translation.getIdentifier());
		return value.orElse(translation.getFallback());
	}

	@Override
	public List<String> translate(Translation translation, List<Language> languages) {
		List<String> result = new ArrayList<>();
		for (Language language : languages) {
			Optional<String> value = this.translations.getOptional(language, translation.getNamespace(), translation.getIdentifier());
			result.add(value.orElse(translation.getFallback()));
		}
		return result;
	}

	@Override
	public Map<Translation, String> translate(List<Translation> translations, Language language) {
		Map<Translation, String> result = new HashMap<>();
		for (Translation translation : translations) {
			Optional<String> value = this.translations.getOptional(language, translation.getNamespace(), translation.getIdentifier());
			result.put(translation, value.orElse(translation.getFallback()));
		}
		return result;
	}

	@Override
	public BiMultiKeyMap<Translation, Language, String> translate(List<Translation> translations, List<Language> languages) {
		BiMultiKeyMap<Translation, Language, String> result = new BiMultiKeyMap<>();
		for (Translation translation : translations) {
			for (Language language : languages) {
				Optional<String> value = this.translations.getOptional(language, translation.getNamespace(), translation.getIdentifier());
				result.put(translation, language, value.orElse(translation.getFallback()));
			}
		}
		return result;
	}

	@Override
	public void dispose() {
		translations.clear();
	}
}
