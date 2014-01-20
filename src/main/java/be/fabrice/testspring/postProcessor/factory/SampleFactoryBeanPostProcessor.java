package be.fabrice.testspring.postProcessor.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import be.fabrice.testspring.utils.MayBeExcluded;

@Component
@MayBeExcluded
public class SampleFactoryBeanPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for(String name:beanNames){
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			try {
				Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
				Service annotated = clazz.getAnnotation(Service.class);
				if(annotated!=null){
					beanDefinition.setScope("singleton");
				}
			} catch (ClassNotFoundException e) {
				throw new BeansException("Class for bean "+name+" nor found",e){};
			}
		}
	}

}
