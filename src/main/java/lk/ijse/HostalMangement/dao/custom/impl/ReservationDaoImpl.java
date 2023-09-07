package lk.ijse.HostalMangement.dao.custom.impl;

import lk.ijse.HostalMangement.dao.custom.ReservationDao;
import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

    private Session session;

    public ReservationDaoImpl(){}

    @Override
    public String Save(ReservationEntity reservationEntity) {
        return (String)session.save(reservationEntity);
    }

    @Override
    public ReservationEntity Get(String reservation_id) {
        return session.get(ReservationEntity.class, reservation_id);
    }

    @Override
    public void Update(ReservationEntity reservationEntity) {
        session.update(reservationEntity);
    }

    @Override
    public void Delete(ReservationEntity reservationEntity) {
        session.delete(reservationEntity);
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

    @Override
    public List<StudentEntity> GetStudentName(String ID) {
        String hql = "SELECT s FROM StudentEntity s WHERE s.StudentId = :studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId", ID);
        List<StudentEntity> list = query.list();
        return list;
    }

    @Override
    public List<RoomEntity> GetKeyMoney(String ID) {
        String hql = "SELECT s FROM RoomEntity s WHERE s.RoomTypeId = :roomtypeid";
        Query query = session.createQuery(hql);
        query.setParameter("roomtypeid", ID);
        List<RoomEntity> list = query.list();
        return list;
    }

    @Override
    public List<ReservationEntity> getReservationDetails() {
        String hql = "FROM ReservationEntity";
        Query query = session.createQuery(hql);
        List<ReservationEntity> list = query.list();
        return list;
    }
}
