package yapiti.watcha.entity;

import android.net.Uri;
import android.text.TextUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by yapiti on 10/10/14.
 */
public class UriDeserializer extends JsonDeserializer<Uri> {
    @Override
    public Uri deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value=jp.getValueAsString();

        if(TextUtils.isEmpty(value)) return null;
        else return Uri.parse(value);
    }
}
