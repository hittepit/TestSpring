package be.fabrice.testspring.postProcessor.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class RuleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for(String name:beanNames){
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			try {
				Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
				Rule annotated = clazz.getAnnotation(Rule.class);
				if(annotated!=null){
					beanDefinition.setScope(annotated.singleton()?"singleton":"prototype");
				}
			} catch (ClassNotFoundException e) {
				throw new BeansException("Class for bean "+name+" not found (really?)",e){};
			}
		}
	}

}
