package services.service;

import application.exceptions.BaseException;
import models.Teacher;
import models.beans.TeacherBean;

import java.util.List;

/**
 * Created by pkonwar on 1/10/2017.
 */
public interface TeachersServiceI {

    /**
     * Find a teacher
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Teacher findTeacher(Long id) throws BaseException;

    /**
     * Register a teacher
     *
     * @param teacherBean
     * @return
     */
    Teacher register(TeacherBean teacherBean) throws BaseException;


    /**
     * Teachers Login
     *
     * @param teacherBean
     * @return
     */
    Teacher login(TeacherBean teacherBean) throws BaseException;


    /**
     * Forgot Password
     *
     * @param teacherBean
     * @return
     */
    Teacher forgotPassword(TeacherBean teacherBean) throws BaseException;

    /**
     * Convert to bean
     *
     * @param teacherList
     * @return
     * @throws BaseException
     */
    List<TeacherBean> convertToTeacherBean(List<Teacher> teacherList) throws BaseException;
}
