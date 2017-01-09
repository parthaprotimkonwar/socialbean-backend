package services;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import services.service.PersonServiceI;

@Named
@Singleton
public class ServicesFactory {

	@Inject
	public PersonServiceI personService;
	
}
