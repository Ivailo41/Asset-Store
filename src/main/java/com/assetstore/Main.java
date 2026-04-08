import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public record Heading(String method, String path) {
}

public record Request(Heading heading, Map<String, String> map) {}

public void main() {
    Heading header = new Heading("GET", "/");
    Map<String, String> map = new HashMap<String, String>();

    Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    map.put("size",  "100MB");
    map.put("file_name", "text.txt");
    map.put("type", "text");
    Request request = new Request(header, map);

    System.out.println(GSON.toJson(request));

}
