package me.hinsinger.hinz.translation.adapter;

import java.lang.reflect.Type;
import java.util.Set;

import com.google.gson.*;

import me.hinsinger.hinz.common.json.adapter.JsonAdapter;
import me.hinsinger.hinz.translation.Translation;
import me.hinsinger.hinz.translation.impl.ComplexTranslation;
import me.hinsinger.hinz.translation.impl.SimpleTranslation;
import me.hinsinger.hinz.translation.placeholder.Placeholder;

public class TranslationJsonAdapter implements JsonAdapter<Translation> {

	@Override
	public JsonElement serialize(Translation src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();
		
		object.addProperty("namespace", src.getNamespace());
		object.addProperty("identifier", src.getIdentifier());
		object.addProperty("fallback", src.getFallback());

		if (src instanceof ComplexTranslation) {
			Set<Placeholder> placeholders = ((ComplexTranslation) src).getPlaceholders();
			
			JsonArray array = new JsonArray();
			
			for (Placeholder p : placeholders) {
				array.add(context.serialize(p, Placeholder.class));
			
			}
			object.add("placeholders", array);
		}

		return object;
	}

	@Override
	public Translation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (!json.isJsonObject())
			throw new JsonParseException("Expected a JSON object");

		JsonObject object = json.getAsJsonObject();

		String namespace = getAsString(object, "namespace");
		String identifier = getAsString(object, "identifier");
		String fallback = getAsString(object, "fallback");

		JsonElement placeholdersElement = object.get("placeholders");

		if (placeholdersElement != null && placeholdersElement.isJsonArray()) {
			ComplexTranslation complex = ComplexTranslation.of(namespace, identifier, fallback);
			
			for (JsonElement e : placeholdersElement.getAsJsonArray()) {
				complex.addPlaceholder(context.deserialize(e, Placeholder.class));
			}
			
			return complex;
		}

		return SimpleTranslation.of(namespace, identifier, fallback);
	}

	private String getAsString(JsonObject obj, String key) {
		JsonElement el = obj.get(key);
		
		if (el == null || !el.isJsonPrimitive()) {
			throw new JsonParseException("Missing or invalid '" + key + "'");
		}
		
		return el.getAsString();
	}
}
