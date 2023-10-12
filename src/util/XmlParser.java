package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @param <T>
 * @author SHUBHAM ROHILLA
 */
public class XmlParser<T> {
    private final Class<T> object;

    public XmlParser(Class<T> object) {
        this.object = object;
    }

    public T xmlStringToJson(String response) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(response);
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);
        return jsonMapper.readValue(json, this.object);
    }

}
