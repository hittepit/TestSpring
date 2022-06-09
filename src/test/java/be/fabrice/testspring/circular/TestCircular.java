package be.fabrice.testspring.circular;

import be.fabrice.testspring.circular.one.not.proxied.ServiceDeux;
import be.fabrice.testspring.circular.one.not.proxied.ServiceUn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCircular {
    private Logger LOGGER = LoggerFactory.getLogger(TestCircular.class);

    @Test
    public void throw_exception_because_circular_dependencies_between_proxied_beans() {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular/proxied/test-circular-spring.xml");
            Assert.fail("Exception expected");
        } catch (BeanCurrentlyInCreationException e) {
            LOGGER.info("Expected exception throw", e);
        }
    }

    @Test
    public void works_if_one_bean_not_proxied() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular/oneNotProxied/test-circular-spring.xml");
        ServiceUn serviceUn = applicationContext.getBean(ServiceUn.class);
        ServiceDeux serviceDeux = applicationContext.getBean(ServiceDeux.class);
    }
}
