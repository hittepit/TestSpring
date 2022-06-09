package be.fabrice.testspring.circular.proxied;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceUnImpl implements ServiceUn{
    @Autowired
    private ServiceDeux serviceDeux;

    @Override
    @Async
    public void asyncMethod() {

    }

    @Override
    public ServiceDeux getServiceDeux() {
        return this.serviceDeux;
    }
}
