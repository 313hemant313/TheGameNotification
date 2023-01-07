package tech.thegamedefault.notification.model.thirdparty.response;

import lombok.Data;

/**
 * { "error": { "message": "Unsupported post request. Object with ID '10458719715459' does not
 * exist, cannot be loaded due to missing permissions, or does not support this operation. Please
 * read the Graph API documentation at https://developers.facebook.com/docs/graph-api", "type":
 * "GraphMethodException", "code": 100, "error_subcode": 33, "fbtrace_id": "AzqfmbScfWZLqVlkriwzH8L"
 * } }
 */
@Data
public class WhatsappErrorResponse {

  ErrorMessage error;

}
