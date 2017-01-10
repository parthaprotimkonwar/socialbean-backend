package repository;

import models.Presenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pkonwar on 1/9/2017.
 */
@Repository
public interface PresenterRepository extends JpaRepository<Presenter, Long>{

    /**
     * Find by email id and password
     * @param emailId
     * @param password
     * @return
     */
    Presenter findByEmailIdAndPassword(String emailId, String password);

    /**
     * Find by email id
     * @param emailId
     * @return
     */
    Presenter findByEmailId(String emailId);
}
