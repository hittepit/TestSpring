package be.fabrice.testspring.postProcessor.definitionRegistry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import be.fabrice.testspring.utils.MayBeExcluded;

@Component
@MayBeExcluded
public class SingletonServiceBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		String[] beanNames = registry.getBeanDefinitionNames();
		for(String name:beanNames){
			BeanDefinition beanDefinition = registry.getBeanDefinition(name);
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
