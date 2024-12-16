package qrcodeapi.data.enums;

import org.springframework.http.MediaType;

import java.util.List;

public enum ImageType {
    PNG("png",MediaType.IMAGE_PNG ), JPEG("jpeg", MediaType.IMAGE_JPEG), GIF("gif", MediaType.IMAGE_GIF);

    private final String mediaFormatInStr;
    private final MediaType mediaType;

    ImageType(String mediaFormatInStr, MediaType mediaType) {
            this.mediaFormatInStr = mediaFormatInStr;
            this.mediaType = mediaType;
    }

    public String getMediaFormatInStr() {
        return mediaFormatInStr;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public static List<String> getFormats() {
        return List.of(PNG.getMediaFormatInStr(), JPEG.getMediaFormatInStr(), GIF.getMediaFormatInStr());
    }

    public static ImageType fromString(String type) {
        return switch (type) {
            case "png" -> PNG;
            case "jpeg" -> JPEG;
            case "gif" -> GIF;
            default -> null;
        };
    }
}
