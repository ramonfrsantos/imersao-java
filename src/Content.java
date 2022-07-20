public class Content {
  private String title;
  private String imageURL;
  private Double rating;

  public Content(String title, String imageURL, Double rating) {
    this.title = title;
    this.imageURL = imageURL;
    this.rating = rating;
  }

  public Content(String title, String imageURL) {
    this.title = title;
    this.imageURL = imageURL;
  }

  public String getTitle() {
    return title;
  }
  
  public String getImageURL() {
    return imageURL;
  }

  public Double getRating() {
    return rating;
  }
}