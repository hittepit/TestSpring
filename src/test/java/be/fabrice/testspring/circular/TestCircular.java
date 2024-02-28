package be.fabrice.testspring.circular;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import be.fabrice.testspring.circular.one.not.proxied.ServiceDeux;
import be.fabrice.testspring.circular.one.not.proxied.ServiceUn;
import be.fabrice.testspring.circular.one.not.proxied.ServiceTrois;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestCircular {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCircular.class);

    @Test
    void throw_exception_because_circular_dependencies_between_proxied_beans() {
        assertThrows(BeanCurrentlyInCreationException.class,
                () -> new ClassPathXmlApplicationContext("classpath:circular/proxied/test-circular-spring.xml"));
    }

    @Test
    void works_if_one_bean_not_proxied() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular/oneNotProxied/test-circular-spring.xml");
        ServiceUn serviceUn = applicationContext.getBean(ServiceUn.class);
        assertNotNull(serviceUn.getServiceDeux());
        ServiceDeux serviceDeux = applicationContext.getBean(ServiceDeux.class);
        assertNotNull(serviceDeux.getServiceTrois());
        ServiceTrois serviceTrois = applicationContext.getBean(ServiceTrois.class);
        assertNotNull(serviceTrois.getServiceUn());
        assertSame(serviceUn, serviceTrois.getServiceUn());
        assertSame(serviceTrois, serviceDeux.getServiceTrois());
        assertSame(serviceDeux, serviceUn.getServiceDeux());
    }

    @Test
    void throw_exception_because_circular_dependencies_between_proxied_beans_with_setters() {
        assertThrows(BeanCurrentlyInCreationException.class, () -> new ClassPathXmlApplicationContext("classpath:circular/setters/test-circular-spring.xml"));
    }
}
