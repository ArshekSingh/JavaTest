package util;

import com.google.gson.Gson;
import org.json.JSONObject;

public class JSONUtils {
    public static JSONObject toJSON(Object object) {
        Gson gson = new Gson();
        return new JSONObject(gson.toJson(object));
    }
}
