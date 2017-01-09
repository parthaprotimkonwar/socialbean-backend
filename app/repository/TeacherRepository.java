package repository;

import models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    /**
     * Find by email id and password
     * @param emailId
     * @param password
     * @return
     */
    Teacher findByEmailIdAndPassword(String emailId, String password);

    /**
     * Find by email id
     * @param emailId
     * @return
     */
    Teacher findByEmailId(String emailId);
}
