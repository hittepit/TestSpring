package be.fabrice.testspring.circular.setters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("serviceTroisSetter")
public class ServiceTroisImpl implements ServiceTrois {
    private ServiceUn serviceUn;

    @Override
    public ServiceUn getServiceUn() {
        return serviceUn;
    }

    @Override
    @Async
    public void asyncMethod() {

    }

    @Autowired
    public void setServiceUn(ServiceUn serviceUn) {
        this.serviceUn = serviceUn;
    }
}
