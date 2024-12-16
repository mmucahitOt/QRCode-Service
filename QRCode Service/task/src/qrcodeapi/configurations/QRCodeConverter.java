package qrcodeapi.configurations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.context.annotation.Configuration;
import qrcodeapi.data.entities.QRCode;

import java.awt.image.BufferedImage;
import java.util.Map;

@Configuration
public class QRCodeConverter {
    QRCodeWriter writer = new QRCodeWriter();

    public QRCode generateQRCode(int width, int height, String data, ErrorCorrectionLevel level) {
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, level);
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return QRCode.of(bufferedImage);
        } catch (WriterException e) {
            // handle the WriterException
            return null;
        }
    }
}
