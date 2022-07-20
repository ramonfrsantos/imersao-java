import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão HTTP e buscar os dados

        // IMDB config
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ContentGetter contentGetter = new ContentGetterIMDB();

        // NASA Config
        // String API_KEY_NASA = "eoJDhVz6fJ6vKzaQHr9hnwGLCXaSUChxRUUbcZKa";
        // String url = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY_NASA
        //         + "&start_date=2022-07-15&end_date=2022-07-18";
        // ContentGetter contentGetter = new ContentGetterNASA();

        var http = new HTTPClient();
        String json = http.getData(url);

        // exibir e manipular os dados
        List<Content> contents = contentGetter.getContents(json);

        var generator = new StickerGenerator();

        // criar parametro start para o rating no stickers
        int startRatingPositioningParameter = 360;

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            String imageURL = content.getImageURL();
            String title = content.getTitle();
            String fileName = "assets/outputs/" + title + " STICKER.png";

            InputStream inputStream = new URL(imageURL)
                    .openStream();

            // String subtitle = "";

            // double ratingStars = Double.parseDouble(movie.get("imDbRating"));
            // for (int i = 1; i <= ratingStars; i++) {
            // subtitle += "\u2B50";
            // startRatingPositioningParameter -= 30;
            // }

            System.out.println(title + "\n" + imageURL + "\n\n");

            generator.createSticker(inputStream, fileName, "TOP", startRatingPositioningParameter);

            // startRatingPositioningParameter = 360;
        }
    }
}