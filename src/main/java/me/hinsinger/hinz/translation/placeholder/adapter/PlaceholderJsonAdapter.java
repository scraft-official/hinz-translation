package me.hinsinger.hinz.translation.placeholder.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import me.hinsinger.hinz.common.json.JsonUtil;
import me.hinsinger.hinz.common.json.adapter.JsonAdapter;
import me.hinsinger.hinz.translation.placeholder.Placeholder;
import me.hinsinger.hinz.translation.placeholder.impl.StaticPlaceholder;

public class PlaceholderJsonAdapter implements JsonAdapter<Placeholder> {

	static {
		JsonUtil.registerAdapter(Placeholder.class, new PlaceholderJsonAdapter());
	}
	
	@Override
	public JsonElement serialize(Placeholder src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();
		
		object.addProperty("name", src.getName());
		object.addProperty("value", src.getValue());
		
		return object;
	}

	@Override
	public Placeholder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (!json.isJsonObject()) throw new JsonParseException("Expected JSON object");

		JsonObject object = json.getAsJsonObject();
		
		JsonElement nameElement = object.get("name");
		JsonElement valueElement = object.get("value");

		if (nameElement == null || valueElement == null) {
			throw new JsonParseException("Missing 'name' or 'value' fields");
		}

		String name = nameElement.getAsString();
		String value = valueElement.getAsString();
		
		return StaticPlaceholder.of(name, value);
	}
}
