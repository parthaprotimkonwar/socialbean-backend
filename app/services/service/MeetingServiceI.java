package services.service;

import application.exceptions.BaseException;
import models.Meeting;
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
    List<Meeting> allMeeting() throws BaseException;

    /**
     * All the classes occured in the past
     *
     * @return
     */
    List<Meeting> pastMeetings() throws BaseException;

    /**
     * get the upcomming meetings
     *
     * @return
     */
    List<Meeting> upcommingMeetings() throws BaseException;

    /**
     * Create a Meeting
     *
     * @param meetingBean
     * @return
     */
    Meeting createMeeting(MeetingBean meetingBean) throws BaseException;

    /**
     * Find the Id based on a cryptic id
     *
     * @param crypticId
     * @return
     */
    Meeting findMeeting(String crypticId) throws BaseException;

    /**
     * Save the recorded URL for a class
     *
     * @param meetingBean
     * @return
     */
    Meeting saveRecordedUrl(MeetingBean meetingBean) throws BaseException;

    /**
     * Convert into meeting bean
     *
     * @param meeting
     * @return
     */
    List<MeetingBean> convertIntoMeetingBean(List<Meeting> meeting) throws BaseException;

}
