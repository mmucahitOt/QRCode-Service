package qrcodeapi.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QRCode {
    private final BufferedImage image;

    private QRCode(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
    }

    private QRCode(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    public static QRCode of(int width, int height) {
        return new QRCode(width, height);
    }
    public static QRCode of(BufferedImage image) {
        return  new QRCode(image);
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}
