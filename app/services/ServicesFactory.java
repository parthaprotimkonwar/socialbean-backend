package services;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import services.service.MeetingServiceI;
import services.service.PersonServiceI;
import services.service.PresenterServiceI;

@Named
@Singleton
public class ServicesFactory {

	@Inject
	public PersonServiceI personService;

	@Inject
	public PresenterServiceI presenterService;

	@Inject
	public MeetingServiceI meetingService;
	
}
