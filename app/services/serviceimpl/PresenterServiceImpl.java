package services.serviceimpl;

import application.enums.STATUS;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import models.Presenter;
import models.beans.PresenterBean;
import org.springframework.transaction.annotation.Transactional;
import repository.PresenterRepository;
import services.service.PresenterServiceI;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pkonwar on 1/10/2017.
 */
@Named
@Singleton
@Transactional
public class PresenterServiceImpl implements PresenterServiceI {

    @Inject
    private PresenterRepository presenterRepository;

    @Override
    public Presenter findPresenter(Long id) throws BaseException {
        try {
            return presenterRepository.findOne(id);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Presenter findPresenterByEmail(String email) throws BaseException {
        try {
            return presenterRepository.findByEmailId(email);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Presenter register(PresenterBean presenterBean) throws BaseException {

        try {
            Presenter thePresenter = presenterRepository.findByEmailId(presenterBean.getEmailId());
            if(thePresenter != null){
                ErrorConstants error = ErrorConstants.DUPLICATE_EMAIL_ID;
                throw new BaseException(error.getErrorCode(), error.getErrorMessage(), null);
            }
            Presenter presenter = new Presenter(presenterBean.getPresenterName(), presenterBean.getEmailId(), presenterBean.getPassword(), presenterBean.getDesignation(), presenterBean.getDepartment(), presenterBean.getImageBlob(), STATUS.ACTIVE);
            return presenterRepository.save(presenter);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_PERSISTANT_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Presenter login(PresenterBean presenterBean) throws BaseException {
        try {
            return presenterRepository.findByEmailIdAndPassword(presenterBean.getEmailId(), presenterBean.getPassword());
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Presenter updatePresenter(PresenterBean presenterBean) throws BaseException {
        try {
            Presenter presenter = findPresenter(presenterBean.getId());
            presenter.setImageBlob(presenterBean.getImageBlob());
            return presenterRepository.save(presenter);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_PERSISTANT_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Presenter forgotPassword(PresenterBean presenterBean) throws BaseException {
        try {
            return presenterRepository.findByEmailId(presenterBean.getEmailId());
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<PresenterBean> convertToPresenterBean(List<Presenter> presenterList) throws BaseException {
        if (presenterList == null) return null;
        try {
            List<PresenterBean> presenterBeanList = new ArrayList<>();
            for (Presenter presenter : presenterList) {
                presenterBeanList.add(presenter.toPresenterBean());
            }
            return presenterBeanList;
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }
}
