package communication.email.processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pkonwar on 2/9/2017.
 */
public class Fields {

    private Map<String, String> fields = new HashMap<>();

    public void addField(String key, String value) {
        fields.put(key, value);
    }

    //returning a safe field
    public Map<String, String> getFields() {
        return new HashMap<>(fields);
    }
}
