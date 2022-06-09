package be.fabrice.testspring.circular.proxied;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceDeuxImpl implements ServiceDeux {
    @Autowired
    private ServiceTrois serviceTrois;

    @Override
    @Async
    public void asyncMethod() {

    }

    @Override
    public ServiceTrois getServiceTrois() {
        return this.serviceTrois;
    }
}
