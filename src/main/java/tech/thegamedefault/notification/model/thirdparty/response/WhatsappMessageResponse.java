package tech.thegamedefault.notification.model.thirdparty.response;

import java.util.List;
import java.util.Map;
import lombok.Data;


/**
 * Sample response: {
 *     "messaging_product": "whatsapp",
 *     "contacts": [
 *     {
 *     "input": "16505076520",
 *     "wa_id": "16505076520"
 *     }
 *     ],
 *     "messages": [
 *     {
 *     "id": "wamid.HBgLMTY1MDUwNzY1MjAVAgARGBI5QTNDQTVCM0Q0Q0Q2RTY3RTcA"
 *     }
 *     ]
 *     }
 */
@Data
public class WhatsappMessageResponse {

  List<Map<String, String>> messages;

}
