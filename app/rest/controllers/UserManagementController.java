package rest.controllers;

import application.enums.STATUS;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import models.Presenter;
import models.beans.PresenterBean;
import play.mvc.Result;
import rest.base.BaseController;
import rest.bean.response.ResponseBean;
import rest.responsedto.ErrorResponse;
import services.ServicesFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by pkonwar on 1/10/2017.
 */
@Named
@Singleton
public class UserManagementController extends BaseController {


    @Inject
    ServicesFactory servicesFactory;


    /**
     * User Register
     *
     * @return
     */
    public Result register() {
        ResponseBean responseBean = null;
        try {
            PresenterBean presenterBean = convertRequestBodyToObject(request().body(), PresenterBean.class);
            presenterBean = servicesFactory.presenterService.register(presenterBean).toPresenterBean();
            responseBean = new ResponseBean(STATUS.SUCCESS, null, presenterBean, null);
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }

    /**
     * User Login
     *
     * @return
     */
    public Result login() {
        ResponseBean responseBean = null;
        try {
            PresenterBean presenterBean = convertRequestBodyToObject(request().body(), PresenterBean.class);
            Presenter presenter = servicesFactory.presenterService.login(presenterBean);
            if (presenter == null) {
                ErrorConstants error = ErrorConstants.INVALID_LOGIN;
                ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode(), error.getErrorMessage());
                responseBean = new ResponseBean(STATUS.FAILURE, null, null, errorResponse);
            } else {
                responseBean = new ResponseBean(STATUS.SUCCESS, null, presenter.toPresenterBean(), null);
            }
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }

    /**
     * User forgets his password
     *
     * @return
     */
    public Result forgotpassword() {
        ResponseBean responseBean = null;
        try {
            PresenterBean presenterBean = convertRequestBodyToObject(request().body(), PresenterBean.class);
            Presenter presenter = servicesFactory.presenterService.forgotPassword(presenterBean);
            if (presenter == null) {
                ErrorConstants error = ErrorConstants.INVALID_LOGIN;
                ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode(), error.getErrorMessage());
                responseBean = new ResponseBean(STATUS.FAILURE, null, null, errorResponse);
            } else {
                //@TODO : Send the password via email
                responseBean = new ResponseBean(STATUS.SUCCESS, null, null, null);
            }
        } catch (BaseException ex) {
            System.out.println(ex.getCause());
            ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
            return errorObjectToJsonResponse(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = unknownErrorResponse();
            return errorObjectToJsonResponse(errorResponse);
        }
        return convertObjectToJsonResponse(responseBean);
    }
}
