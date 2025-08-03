package me.hinsinger.hinz.translation.language.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import me.hinsinger.hinz.common.json.JsonUtil;
import me.hinsinger.hinz.common.json.adapter.JsonAdapter;
import me.hinsinger.hinz.translation.language.Language;
import me.hinsinger.hinz.translation.language.impl.SimpleLanguage;

public class LanguageJsonAdapter implements JsonAdapter<Language> {

	static {
		JsonUtil.registerAdapter(Language.class, new LanguageJsonAdapter());
	}
	
	@Override
    public JsonElement serialize(Language src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        
        obj.addProperty("code", src.getCode());
        obj.addProperty("name", src.getName());
        
        return obj;
    }

    @Override
    public Language deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        
        String code = obj.get("code").getAsString();
        String name = obj.get("name").getAsString();
        
        return SimpleLanguage.of(code, name);
    }
}
