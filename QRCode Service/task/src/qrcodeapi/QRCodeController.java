package qrcodeapi;


import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qrcodeapi.configurations.BodyConverter;
import qrcodeapi.configurations.QRCodeConverter;
import qrcodeapi.data.entities.QRCode;
import qrcodeapi.data.enums.ImageType;
import qrcodeapi.errors.Error;
import qrcodeapi.validation.QRCodeValidation;

import java.awt.image.BufferedImage;

@Controller
public class QRCodeController {

    @GetMapping("/api/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/api/qrcode")
    public ResponseEntity<?> getImage(
            @RequestParam(value = "contents", required = false) String contents,
            @RequestParam(value = "size", required = false, defaultValue = "250") Integer size,
            @RequestParam(value = "correction", required = false, defaultValue = "L") String correction,
            @RequestParam(value = "type", required = false, defaultValue = "png") String type,
            QRCodeValidation qrCodeValidation,
            BodyConverter bodyConverter,
            QRCodeConverter qrcodeGenerator
    ) {
        ResponseEntity<Error> errorResponseEntity = qrCodeValidation.validateQueryParams(contents, size, correction, type);
        if (errorResponseEntity != null) {
            return  errorResponseEntity;
        }

        ErrorCorrectionLevel correctionLevel = ErrorCorrectionLevel.valueOf(correction);
        QRCode qrCode = qrcodeGenerator.generateQRCode(size, size, contents, correctionLevel);
        assert ImageType.fromString(type) != null;

        BufferedImage image = qrCode.getBufferedImage();
        MediaType mediaType = ImageType.fromString(type).getMediaType();

        byte[] body = bodyConverter.convertBufferedImageToByteArray(image, type);
        return ResponseEntity.ok().contentType(mediaType).body(body);
    }
}
