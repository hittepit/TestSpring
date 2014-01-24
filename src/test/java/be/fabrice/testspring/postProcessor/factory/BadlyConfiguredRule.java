package be.fabrice.testspring.postProcessor.factory;

import org.springframework.context.annotation.Scope;

/**
 * Cette classe est configurée comme une règle, mais le développeur a maladroitement configuré un scope singleton. 
 * Si tout se passe bien, le bean doit être un prototype au final.
 * @author fabrice.claes
 *
 */
@Rule
@Scope("singleton")
public class BadlyConfiguredRule {

}
