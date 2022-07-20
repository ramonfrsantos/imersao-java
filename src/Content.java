public class Content {
  private String title;
  private String imageURL;

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
}