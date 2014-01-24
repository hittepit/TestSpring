package be.fabrice.testspring.postProcessor.factory;

import org.springframework.context.annotation.Scope;

@Rule(singleton=true)
@Scope("prototype") //Ooops
public class PoorlyConfiguredRealSingletonRule {

}
