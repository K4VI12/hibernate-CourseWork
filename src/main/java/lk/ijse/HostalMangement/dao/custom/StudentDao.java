package lk.ijse.HostalMangement.dao.custom;

import lk.ijse.HostalMangement.dao.CrudDao;
import lk.ijse.HostalMangement.dao.SuperDao;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;

import java.util.List;

public interface StudentDao extends CrudDao<StudentEntity, String> {
    void SetSession(Session session);
    List<StudentEntity>getAllStudentDetails();
}
