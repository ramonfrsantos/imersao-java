import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentGetterIMDB implements ContentGetter {
  public List<Content> getContents(String json) {

    // separar e extrair somente os dados que interessam
    var parser = new JSONParser();
    List<Map<String, String>> attributesList = parser.parse(json);

    List<Content> contents = new ArrayList<Content>();

    // popular a lista de conteudos
    for (Map<String, String> attributes : attributesList) {
      String title = attributes.get("title");
      String imageURL = attributes.get("image").replaceAll(
          "._V1_UX128_CR0,3,128,176_AL_", "._V1_UX800_CR0,3,800,1177_AL_");
      String rating = attributes.get("imDbRating");
      
      double ratingDouble = Double.parseDouble(rating);

      var content = new Content(title, imageURL, ratingDouble);
      contents.add(content);
    }

    return contents;
  }
}
