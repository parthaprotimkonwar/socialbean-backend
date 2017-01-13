package repository;

import models.Meeting;
import models.Presenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    /**
     * Find all meeting by presentor
     *
     * @param presenter
     * @return
     */
    List<Meeting> findAllByPresenter(Presenter presenter);

    /**
     * Past meetings
     *
     * @param startDateTime
     * @return
     */
    List<Meeting> findByStartDateTimeBeforeAndPresenter(Date startDateTime, Presenter presenter);

    /**
     * Upcoming meetings
     *
     * @param startDateTime
     * @return
     */
    List<Meeting> findByStartDateTimeAfterAndPresenter(Date startDateTime, Presenter presenter);

    /**
     * Find meeting my presentors token
     * @param token
     * @return
     */
    Meeting findByPresenterToken(String token);

    /**
     * Find my attendees token
     * @param token
     * @return
     */
    Meeting findByAttendeesToken(String token);
}
