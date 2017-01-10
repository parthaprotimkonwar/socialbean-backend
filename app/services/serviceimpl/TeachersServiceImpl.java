package services.serviceimpl;

import application.enums.STATUS;
import application.exceptions.BaseException;
import application.exceptions.ErrorConstants;
import models.Teacher;
import models.beans.TeacherBean;
import org.springframework.transaction.annotation.Transactional;
import repository.TeacherRepository;
import services.service.TeachersServiceI;

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
public class TeachersServiceImpl implements TeachersServiceI {

    @Inject
    private TeacherRepository teacherRepository;

    @Override
    public Teacher findTeacher(Long id) throws BaseException {
        try {
            return teacherRepository.findOne(id);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Teacher register(TeacherBean teacherBean) throws BaseException {

        try {
            Teacher teacher = new Teacher(teacherBean.getTeacherName(), teacherBean.getEmailId(), teacherBean.getPassword(), teacherBean.getImageBlob(), STATUS.ACTIVE);
            return teacherRepository.save(teacher);
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_PERSISTANT_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Teacher login(TeacherBean teacherBean) throws BaseException {
        try {
            return teacherRepository.findByEmailIdAndPassword(teacherBean.getEmailId(), teacherBean.getPassword());
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public Teacher forgotPassword(TeacherBean teacherBean) throws BaseException {
        try {
            return teacherRepository.findByEmailId(teacherBean.getEmailId());
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }

    @Override
    public List<TeacherBean> convertToTeacherBean(List<Teacher> teacherList) throws BaseException {
        try {
            List<TeacherBean> teacherBeanList = new ArrayList<>();
            for (Teacher teacher : teacherList) {
                teacherBeanList.add(teacher.toTeacherBean());
            }
            return teacherBeanList;
        } catch (Exception ex) {
            ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
            throw new BaseException(error.getErrorCode(), error.getErrorMessage(), ex.getCause());
        }
    }
}
