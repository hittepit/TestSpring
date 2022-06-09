package be.fabrice.testspring.circular.one.not.proxied;

public interface ServiceDeux {
    void notAsyncMethod();
    ServiceTrois getServiceTrois();
}
