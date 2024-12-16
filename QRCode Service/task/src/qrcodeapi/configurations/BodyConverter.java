package qrcodeapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Configuration
public class BodyConverter {

    public byte[] convertBufferedImageToByteArray(BufferedImage bufferedImage, String mediaType) {
        try (var baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, mediaType, baos); // writing the image in the PNG format
            return baos.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
