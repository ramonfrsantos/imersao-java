import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

  void createSticker(InputStream inputStream, String fileName, String subtitle, int startRatingParameter) throws Exception {
    // leitura da imagem
    // InputStream inputStream = new FileInputStream(new File("assets/inputs/movie.jpg"));
    // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@.jpg").openStream();
    BufferedImage originalImage = ImageIO.read(inputStream);

    // cria nova imagem em memória com transparência e tamanho novo
    int width = 800;
    int height = 1170;
    int newHeight = height + 150;

    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // copiar a imagem original pra nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();

    graphics.drawImage(originalImage, 0, 0, width, height, null);

    // configurar a fonte
    var font = new Font("Courrier", Font.PLAIN, 78);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // escrever uma frase na nova imagem
    graphics.drawString(subtitle, startRatingParameter, newHeight - 50);

    // escrever nova imagem num arquivo
    ImageIO.write(newImage, "png", new File(fileName));

    graphics.dispose();
  }
}