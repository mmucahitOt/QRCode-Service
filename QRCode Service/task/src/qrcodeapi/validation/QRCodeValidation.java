package qrcodeapi.validation;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import qrcodeapi.data.enums.ImageType;
import qrcodeapi.errors.Error;

import java.util.Arrays;

@Configuration
public class QRCodeValidation {
    static final String SizeErrorMessage = "Image size must be between 150 and 350 pixels";
    static final String ImageTypeErrorMessage = "Only png, jpeg and gif image types are supported";
    static final String ContentErrorMessage = "Contents cannot be null or blank";
    static final String ErrorCorrectionLevelErrorMessage = "Permitted error correction levels are L, M, Q, H";

    private Error validateContents(String contents) {
        if (contents == null || contents.trim().isEmpty()) {
            return Error.of(ContentErrorMessage);
        }
        return null;
    }

    private Error validateSize(Integer size) {
        if (size < 150 || size > 350) {
            return Error.of(SizeErrorMessage);
        }
        return null;
    }

    private Error validateErrorCorrectionLevel(String level) {
        if (Arrays.stream(ErrorCorrectionLevel.values()).noneMatch(e -> e.name().equalsIgnoreCase(level))) {
            return Error.of(ErrorCorrectionLevelErrorMessage);
        }
        return null;
    }

    private Error validateType(String type) {
        if (!ImageType.getFormats().contains(type)) {
            return Error.of(ImageTypeErrorMessage);
        }
        return null;
    }

    public ResponseEntity<Error> validateQueryParams(String contents, Integer size, String correction, String type) {
        Error contentError = validateContents(contents);
        if (contentError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contentError);
        }

        Error sizeError = validateSize(size);
        if (sizeError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sizeError);
        }

        Error correctionLevelError = validateErrorCorrectionLevel(correction);
        if (correctionLevelError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(correctionLevelError);
        }

        Error typeError = validateType(type);
        if (typeError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(typeError);
        }

        return null;
    }

}
