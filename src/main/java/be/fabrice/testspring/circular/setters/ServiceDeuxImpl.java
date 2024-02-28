package be.fabrice.testspring.circular.setters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("serviceDeuxSetter")
public class ServiceDeuxImpl implements ServiceDeux {
    private ServiceTrois serviceTrois;

    @Override
    @Async
    public void asyncMethod() {

    }

    @Override
    public ServiceTrois getServiceTrois() {
        return this.serviceTrois;
    }

    @Autowired
    public void setServiceTrois(ServiceTrois serviceTrois) {
        this.serviceTrois = serviceTrois;
    }
}
