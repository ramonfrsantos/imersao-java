import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HTTPClient {
  public String getData(String url) {
    try {
      
      URI uri = URI.create(url);
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(uri).build();
      HttpResponse<String> response;
      response = client.send(request, BodyHandlers.ofString());

      return response.body();

    } catch (IOException | InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
}
