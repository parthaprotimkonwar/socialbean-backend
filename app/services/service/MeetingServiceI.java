package services.service;

import application.exceptions.BaseException;
import models.Meeting;
import models.Presenter;
import models.beans.MeetingBean;

import java.util.List;

/**
 * Created by pkonwar on 1/9/2017.
 */
public interface MeetingServiceI {

    /**
     * All meetings available
     *
     * @return
     */
    List<Meeting> allMeeting(Presenter presenter) throws BaseException;

    /**
     * All the classes occured in the past
     *
     * @return
     */
    List<Meeting> pastMeetings(Presenter presenter) throws BaseException;

    /**
     * get the upcomming meetings
     *
     * @return
     */
    List<Meeting> upcommingMeetings(Presenter presenter) throws BaseException;

    /**
     * Create a Meeting
     *
     * @param meetingBean
     * @return
     */
    Meeting createMeeting(MeetingBean meetingBean, Presenter presenter) throws BaseException;

    /**
     * Attender's meeting based on token
     * @param crypticId
     * @return
     * @throws BaseException
     */
    Meeting findMeetingOfAttendee(String crypticId) throws BaseException;

    /**
     * Persentor's meeting based on token
     * @param crypticId
     * @return
     * @throws BaseException
     */
    Meeting findMeetingOfPersentor(String crypticId) throws BaseException;

    /**
     * Save the recorded URL for a class
     * @param meetingId
     * @param recordedUrl
     * @return
     * @throws BaseException
     */
    Meeting saveRecordedUrl(Long meetingId, String recordedUrl) throws BaseException;

    /**
     * Convert into meeting bean
     *
     * @param meeting
     * @return
     */
    List<MeetingBean> convertIntoMeetingBean(List<Meeting> meeting) throws BaseException;

}
