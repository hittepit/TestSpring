package be.fabrice.testspring.circular.one.not.proxied;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ServiceTroisImpl implements ServiceTrois{
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
