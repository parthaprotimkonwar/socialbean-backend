package services;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import services.service.MeetingServiceI;
import services.service.PersonServiceI;
import services.service.TeachersServiceI;

@Named
@Singleton
public class ServicesFactory {

	@Inject
	public PersonServiceI personService;

	@Inject
	public TeachersServiceI teachersService;

	@Inject
	public MeetingServiceI meetingService;
	
}
