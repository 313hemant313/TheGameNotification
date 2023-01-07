package tech.thegamedefault.notification.service;

import tech.thegamedefault.notification.exception.NotificationException;
import tech.thegamedefault.notification.utility.StringUtility;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.nio.file.Paths;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class NotificationTemplateEngine {

  @Autowired
  Configuration freemarkerConfiguration;

  public String process(String templateName, String templateDirectory, Map<String, Object> data) {
    final String TEMPLATE_EXT = "ftl";
    String templateLocation = Paths.get(templateDirectory, templateName, StringUtility.DOT_STRING,
        TEMPLATE_EXT).toString();
    try {
      Template template = freemarkerConfiguration.getTemplate(templateLocation);
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
    } catch (Exception e) {
      throw new NotificationException(
          String.format("%s: Failed to process template with templateName: %s with error: %s",
              this.getClass().getSimpleName(), templateName, e.getMessage()));
    }
  }

}
