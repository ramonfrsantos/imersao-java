import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentGetterLinguagensAPI implements ContentGetter {
  public List<Content> getContents(String json) {

    // separar e extrair somente os dados que interessam
    var parser = new JSONParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contents = new ArrayList<Content>();

    // popular a lista de conteudos
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String imageURL = attributes.get("image");
      String ranking = attributes.get("ranking");

      System.out.println(title);
      System.out.println(imageURL);
      System.out.println(ranking);
      System.out.println();

      double rankingDouble = Double.parseDouble(ranking);

      var content = new Content(title, imageURL, rankingDouble);
      contents.add(content);
    }

    return contents;
  }
}
