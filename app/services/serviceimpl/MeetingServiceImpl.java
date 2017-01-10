package services.serviceimpl;

import application.encryption.MeetingToken;
import application.enums.USER_TYPE;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import models.Meeting;
import models.Presenter;
import models.beans.MeetingBean;
import org.springframework.transaction.annotation.Transactional;
import repository.MeetingRepository;
import services.service.MeetingServiceI;
import services.service.PresenterServiceI;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pkonwar on 1/10/2017.
 */
@Named
@Singleton
@Transactional
public class MeetingServiceImpl implements MeetingServiceI {

    @Inject
    private MeetingRepository meetingRepository;

    @Inject
    private PresenterServiceI presenterService;

    @Override
    public List<Meeting> allMeeting() throws BaseException {
        try {
            return meetingRepository.findAll();
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<Meeting> pastMeetings() throws BaseException {
        try {
            Date now = new Date();
            return meetingRepository.findByStartDateTimeBefore(now);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<Meeting> upcommingMeetings() throws BaseException {
        try {
            Date now = new Date();
            return meetingRepository.findByStartDateTimeAfter(now);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting createMeeting(MeetingBean meetingBean) throws BaseException {
        try {
            Presenter presenter = presenterService.findPresenter(meetingBean.getPresenterBean().getId());
            Meeting meeting = new Meeting(meetingBean.getTitle(), meetingBean.getStartDateTime(), meetingBean.getDuration(), null, null, null, presenter);
            meeting = meetingRepository.save(meeting);
            MeetingToken presenterToken = new MeetingToken(meeting.getId(), presenter.getId(), meetingBean.getStartDateTime(), USER_TYPE.PRESENTER);
            MeetingToken attendeeToken = new MeetingToken(meeting.getId(), presenter.getId(), meetingBean.getStartDateTime(), USER_TYPE.ATTENDEE);

            meeting.setPresenterToken(presenterToken.encode());
            meeting.setAttendeesToken(attendeeToken.encode());
            return meetingRepository.save(meeting);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting findMeeting(String token) throws BaseException {
        try {
            MeetingToken meetingToken = new MeetingToken().decode(token);
            return meetingRepository.findOne(meetingToken.getMeetingId());
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting saveRecordedUrl(Long meetingId, String recordedUrl) throws BaseException {
        try {
            Meeting meeting = meetingRepository.findOne(meetingId);
            meeting.setRecordedUrl(recordedUrl);
            return meetingRepository.save(meeting);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_PERSISTANT_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<MeetingBean> convertIntoMeetingBean(List<Meeting> meetingList) throws BaseException {
        try {
            List<MeetingBean> meetingBeanList = new ArrayList<>();
            for(Meeting meeting : meetingList) {
                meetingBeanList.add(meeting.toMeetingBean());
            }
            return meetingBeanList;
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }
}
