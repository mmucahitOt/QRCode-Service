package qrcodeapi.errors;

public class Error {
    public String error;

    Error(String message) {
        this.error = message;
    }
    public static Error of(String message) {
        return new Error(message);
    }
}
