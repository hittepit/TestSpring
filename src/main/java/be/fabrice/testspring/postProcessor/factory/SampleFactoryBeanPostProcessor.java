package be.fabrice.testspring.postProcessor.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleFactoryBeanPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Map<String,Object> beans = beanFactory.getBeansWithAnnotation(CustomAnnotation.class);
		for(Object bean:beans.values()){
			try {
				Class<?> clazz = bean.getClass();
				CustomAnnotation annot = clazz.getAnnotation(CustomAnnotation.class);
				Method method = clazz.getMethod("setValue", String.class);
				method.invoke(bean, annot.value());
			} catch (Exception e) {
				throw new BeansException("Erreur du FactoryBeanPostProcessor",e) {};
			}
		}
	}

}
