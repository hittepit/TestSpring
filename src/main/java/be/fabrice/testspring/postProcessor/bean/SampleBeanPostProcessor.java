package be.fabrice.testspring.postProcessor.bean;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SampleBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		try {
			Class<?> clazz = bean.getClass();
			CustomAnnotation annot = clazz.getAnnotation(CustomAnnotation.class);
			if(annot!=null){
				Method method = clazz.getMethod("setValue", String.class);
				method.invoke(bean, annot.value());
			}
		} catch (Exception e) {
			throw new BeansException("Erreur du FactoryBeanPostProcessor",e) {};
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		try {
			Class<?> clazz = bean.getClass();
			CustomAnnotation annot = clazz.getAnnotation(CustomAnnotation.class);
			if(annot!=null){
				Method get = clazz.getMethod("getValue", new Class[]{});
				String value = (String) get.invoke(bean, new Object[]{});
				Method method = clazz.getMethod("setValue", String.class);
				method.invoke(bean, value+'0');
			}
		} catch (Exception e) {
			throw new BeansException("Erreur du FactoryBeanPostProcessor",e) {};
		}
		return bean;
	}
}
