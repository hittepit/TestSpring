package be.fabrice.testspring.circular.one.not.proxied;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceDeuxImpl implements ServiceDeux {
    @Autowired
    private ServiceTrois serviceTrois;

    @Override
    public void notAsyncMethod() {

    }

    @Override
    public ServiceTrois getServiceTrois() {
        return this.serviceTrois;
    }
}
