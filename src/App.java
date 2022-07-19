import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão HTTP e buscar os top 250 movies
        // String API_KEY = "549a5d8b";
        // String url = "https://api.mocki.io/v2/" + API_KEY;
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        URI uri = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).build();

        String bodyJSON = client.send(request, HttpResponse.BodyHandlers
            .ofString()).body();

        // separar e extrair somente os dados que interessam
        var parser = new JSONParser();

        List<Map<String, String>> moviesList = parser.parse(bodyJSON);

        // criar parametro start para o rating no stickers
        int startRatingParameter = 360;

        // exibir e manipular os dados
        var generator = new StickerGenerator();
        for (Map<String, String> movie : moviesList) {
            String imageURL = movie.get("image")
                .replaceAll(
                    "._V1_UX128_CR0,3,128,176_AL_"
                    , "._V1_UX800_CR0,3,800,1177_AL_");

            String title = movie.get("title");
            String fileName = "assets/outputs/" + title + " STICKER.png";

            InputStream inputStream = new URL(imageURL)
                    .openStream();

            String subtitle = "";

            double ratingStars = Double.parseDouble(movie.get("imDbRating"));
            for (int i = 1; i <= ratingStars; i++) {
                subtitle += "\u2B50";
                startRatingParameter -= 30;
            }
            System.out.println(title + "\n" + imageURL + "\n\n");

            generator.createSticker(inputStream, fileName, subtitle, startRatingParameter);

            startRatingParameter = 360;
        }
    }
}