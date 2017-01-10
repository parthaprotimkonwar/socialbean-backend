package services.service;

import application.exceptions.BaseException;
import models.Presenter;
import models.beans.PresenterBean;

import java.util.List;

/**
 * Created by pkonwar on 1/10/2017.
 */
public interface PresenterServiceI {

    /**
     * Find a presenter
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Presenter findPresenter(Long id) throws BaseException;

    /**
     * Register a presenter
     *
     * @param presenterBean
     * @return
     */
    Presenter register(PresenterBean presenterBean) throws BaseException;


    /**
     * Presenter Login
     *
     * @param presenterBean
     * @return
     */
    Presenter login(PresenterBean presenterBean) throws BaseException;


    /**
     * Forgot Password
     *
     * @param presenterBean
     * @return
     */
    Presenter forgotPassword(PresenterBean presenterBean) throws BaseException;

    /**
     * Convert to bean
     *
     * @param presenterList
     * @return
     * @throws BaseException
     */
    List<PresenterBean> convertToPresenterBean(List<Presenter> presenterList) throws BaseException;
}
