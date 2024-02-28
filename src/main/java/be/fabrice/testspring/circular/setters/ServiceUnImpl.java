package be.fabrice.testspring.circular.setters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("serviceUnSetter")
public class ServiceUnImpl implements ServiceUn {
    private ServiceDeux serviceDeux;

    @Override
    @Async
    public void asyncMethod() {

    }

    @Override
    public ServiceDeux getServiceDeux() {
        return this.serviceDeux;
    }

    @Autowired
    public void setServiceDeux(ServiceDeux serviceDeux) {
        this.serviceDeux = serviceDeux;
    }
}
