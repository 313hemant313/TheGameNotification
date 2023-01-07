package tech.thegamedefault.notification.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperUtility {


  private static final ObjectMapper INSTANCE = new ObjectMapper();

  static {
    INSTANCE.registerModule(new JavaTimeModule());
    INSTANCE.findAndRegisterModules();
    INSTANCE.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    INSTANCE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    INSTANCE.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  private ObjectMapperUtility() {
  }

  public static ObjectMapper getInstance() {
    return INSTANCE;
  }

  public static <U, O> O updateObject(U newObject, O existingObject) {
    try {
      ObjectReader objectReader = INSTANCE.readerForUpdating(existingObject);
      String updateWith = INSTANCE.writeValueAsString(newObject);
      return objectReader.readValue(updateWith);
    } catch (IOException e) {
      log.error("Unable to update object {}", existingObject.getClass().getSimpleName(), e);
      return null;
    }
  }

  public static <I, O> List<O> convertValue(List<I> value, Class<O> requiredValueType) {
    return value.stream()
        .map(object -> convertValue(object, requiredValueType))
        .collect(Collectors.toList());
  }

  public static <I, O> O convertValue(I value, Class<O> requiredValueType) {
    return INSTANCE.convertValue(value, requiredValueType);
  }

  public static <T> T readValue(String source, Class<T> valueType) {
    try {
      return INSTANCE.readValue(source, valueType);
    } catch (IOException e) {
      log.error("Unable to convert to object {}", valueType.getName(), e);
      return null;
    }
  }

  public static <T> String writeValueAsString(T object) {
    try {
      return INSTANCE.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Error parsing object to string ", e);
      return null;
    }
  }

}
