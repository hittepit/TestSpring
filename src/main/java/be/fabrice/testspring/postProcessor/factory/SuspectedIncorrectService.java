package be.fabrice.testspring.postProcessor.factory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class SuspectedIncorrectService {

}
