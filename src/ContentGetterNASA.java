import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentGetterNASA implements ContentGetter {
  public List<Content> getContents(String json) {

    // separar e extrair somente os dados que interessam
    var parser = new JSONParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contents = new ArrayList<Content>();

    // popular a lista de conteudos
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String imageURL = attributes.get("url");

      var content = new Content(title, imageURL);
      contents.add(content);
    }

    return contents;
  }
}
