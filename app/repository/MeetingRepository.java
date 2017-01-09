package repository;

import models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    /**
     * Past meetings
     * @param startDateTime
     * @return
     */
    List<Meeting> findByStartDateTimeBefore(Date startDateTime);

    /**
     * Upcoming meetings
     * @param startDateTime
     * @return
     */
    List<Meeting> findByStartDateTimeAfter(Date startDateTime);
}
