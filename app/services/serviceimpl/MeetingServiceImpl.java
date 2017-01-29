package services.serviceimpl;

import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import models.Meeting;
import models.Presenter;
import models.beans.MeetingBean;
import org.springframework.transaction.annotation.Transactional;
import repository.MeetingRepository;
import services.service.MeetingServiceI;

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

    @Override
    public List<Meeting> allMeeting(Presenter presenter) throws BaseException {
        try {
            return meetingRepository.findAllByPresenter(presenter);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<Meeting> pastMeetings(Presenter presenter) throws BaseException {
        try {
            Date now = new Date();
            return meetingRepository.findByStartDateTimeBeforeAndPresenter(now, presenter);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<Meeting> upcommingMeetings(Presenter presenter) throws BaseException {
        try {
            Date now = new Date();
            return meetingRepository.findByStartDateTimeAfterAndPresenter(now, presenter);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting createMeeting(MeetingBean meetingBean, Presenter presenter) throws BaseException {
        try {
            Meeting meeting = new Meeting(meetingBean.getTitle(), meetingBean.getDescription(), meetingBean.getStartDateTime(), meetingBean.getDuration(), meetingBean.getPresenterToken(), meetingBean.getAttendeesToken(), null, presenter);
            //meeting = meetingRepository.save(meeting);
            //MeetingToken presenterToken = new MeetingToken(meeting.getId(), presenter.getId(), meetingBean.getStartDateTime(), USER_TYPE.PRESENTER);
            //MeetingToken attendeeToken = new MeetingToken(meeting.getId(), presenter.getId(), meetingBean.getStartDateTime(), USER_TYPE.ATTENDEE);
            //meeting.setPresenterToken(presenterToken.encode());
            //meeting.setAttendeesToken(attendeeToken.encode());
            return meetingRepository.save(meeting);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting findMeetingOfAttendee(String crypticId) throws BaseException {
        try {
            return meetingRepository.findByAttendeesToken(crypticId);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Meeting findMeetingOfPersentor(String crypticId) throws BaseException {
        try {
            return meetingRepository.findByPresenterToken(crypticId);
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
        if (meetingList == null) return null;

        try {
            List<MeetingBean> meetingBeanList = new ArrayList<>();
            for (Meeting meeting : meetingList) {
                meetingBeanList.add(meeting.toMeetingBean());
            }
            return meetingBeanList;
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }
}