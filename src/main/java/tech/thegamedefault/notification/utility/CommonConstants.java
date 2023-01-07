package tech.thegamedefault.notification.utility;

public class CommonConstants {

  public enum Status {
    SUCCESS, VALIDATION_FAILED, FAILED, FAILED_AT_THIRD_PARTY, PENDING
  }

  public enum Language {
    ENGLISH
  }

  public enum ErrorMessage {
    BAD_REQUEST("Bad request, please check the request before trying again."),
    CUSTOM_NOT_FOUND("%s not found"),
    CUSTOM_MANDATORY_FIELD("%s is/are required"),
    CUSTOM_ALREADY_EXISTS("%s already exists"),
    NOT_FOUND("Resource not found"), SOMETHING_WENT_WRONG(
        "Something went wrong, please try again in sometime.");

    private final String message;

    ErrorMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return this.message;
    }
  }

}
