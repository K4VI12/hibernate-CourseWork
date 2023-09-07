package lk.ijse.HostalMangement.bo.custom.Impl;

import lk.ijse.HostalMangement.bo.custom.StudentBo;
import lk.ijse.HostalMangement.config.SessionFactoryConfig;
import lk.ijse.HostalMangement.dao.DaoFactory;
import lk.ijse.HostalMangement.dao.custom.StudentDao;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentBoImpl implements StudentBo {

    StudentDao studentDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.STUDENT);

    @Override
    public String SaveStudent(StudentDTO studentDto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDao.SetSession(session);
            String saveId = studentDao.Save(studentDto.ToEntity());
            transaction.commit();
            session.close();
            return saveId;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return "-1";
        }
    }

    @Override
    public StudentDTO getStudent(String student_id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            studentDao.SetSession(session);
            StudentEntity student = studentDao.Get(student_id);
            session.close();
            return student.ToDto();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateStudent(StudentDTO studentDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            studentDao.SetSession(session);
            studentDao.Update(studentDTO.ToEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean DeleteStudent(StudentDTO studentDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDao.SetSession(session);
            studentDao.Delete(studentDTO.ToEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            studentDao.SetSession(session);
            List<StudentEntity>student = studentDao.getAllStudentDetails();
            for(StudentEntity studentEntity : student){
                System.out.println(studentEntity.getStudentId());
            }
            session.close();
            return student;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
