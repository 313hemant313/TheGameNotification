package tech.thegamedefault.notification.utility;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtility implements ApplicationContextAware {

  private static ApplicationContext context;

  public static ApplicationContext getContext() {
    return context;
  }

  public static <T> T getBean(Class<T> bean) {
    return getContext().getBean(bean);
  }

  public static <T> T getBean(String beanName, Class<T> bean) {
    return getContext().getBean(beanName, bean);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

}