package lk.ijse.HostalMangement.dao.custom;

import lk.ijse.HostalMangement.dao.CrudDao;
import lk.ijse.HostalMangement.dao.SuperDao;
import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;

import java.util.List;

public interface ReservationDao extends CrudDao<ReservationEntity, String> {
    void SetSession(Session session);
    List<StudentEntity> GetStudentName(String ID);
    List<RoomEntity> GetKeyMoney(String ID);
    List<ReservationEntity> getReservationDetails();
}
