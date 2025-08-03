package me.hinsinger.hinz.translation.provider;

import java.util.List;
import java.util.Map;

import me.hinsinger.hinz.common.lifecycle.phase.Disposable;
import me.hinsinger.hinz.common.lifecycle.phase.Initializable;
import me.hinsinger.hinz.common.multi.key.map.impl.BiMultiKeyMap;
import me.hinsinger.hinz.translation.Translation;
import me.hinsinger.hinz.translation.language.Language;

public interface TranslationProvider extends Initializable, Disposable {

	public BiMultiKeyMap<Translation, Language, String> translate(List<Translation> translations, List<Language> languages);
	
	public Map<Translation, String> translate(List<Translation> translations, Language languages);
	
	public List<String> translate(Translation translation, List<Language> language);
	
	public String translate(Translation translation, Language language);
}
