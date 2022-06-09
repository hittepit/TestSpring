package be.fabrice.testspring.circular.proxied;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceTroisImpl implements ServiceTrois{
    @Autowired
    private ServiceUn serviceUn;

    @Override
    public ServiceUn getServiceUn() {
        return serviceUn;
    }

    @Override
    @Async
    public void asyncMethod() {

    }
}
