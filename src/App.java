import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão HTTP e buscar os top 250 filmes
        String API_KEY = "549a5d8b";
        String url = "https://api.mocki.io/v2/" + API_KEY;
        
        URI uri = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).build();

        String bodyJSON = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        // separar e extrair somente os dados que interessam, como título, avaliação, poster
        var parser = new JSONParser();

        List<Map<String, String>> moviesList = parser.parse(bodyJSON);

        //exibir e manipular os dados
        for (Map<String,String> filme : moviesList) {
            System.out.print("\u001b[1m Título: ");
            System.out.println(filme.get("title"));
            
            System.out.print("\u001b[1m Pôster: ");
            System.out.println(filme.get("image"));

            System.out.print("\u001b[1m \u001b[44;1m Classificação: ");
            System.out.println("\u001b[46;1m " + filme.get("imDbRating"));

            double ratingStars = Double.parseDouble(filme.get("imDbRating"));
            for(int i=1; i<=ratingStars; i++){
                System.out.print("\u2B50");
            }

            System.out.println("\n");
        }
    }
}