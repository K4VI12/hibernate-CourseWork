package lk.ijse.HostalMangement.bo.custom.Impl;

import lk.ijse.HostalMangement.bo.custom.ReservationBo;
import lk.ijse.HostalMangement.config.SessionFactoryConfig;
import lk.ijse.HostalMangement.dao.DaoFactory;
import lk.ijse.HostalMangement.dao.custom.ReservationDao;
import lk.ijse.HostalMangement.dao.custom.RoomDao;
import lk.ijse.HostalMangement.dto.ReservationDTO;
import lk.ijse.HostalMangement.dto.RoomDTO;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationBoImpl implements ReservationBo {

    ReservationDao reservationDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.RESERVATION);
    RoomDao roomDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROOM);

    @Override
    public String SaveReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction  = session.beginTransaction();
        try {
            reservationDao.SetSession(session);
            roomDao.SetSession(session);
            String save = reservationDao.Save(reservationDTO.ToEntity());
            roomDao.Update(roomDTO.ToEntity());
            transaction.commit();
            session.close();
            return save;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return "-1";
        }
    }

    @Override
    public ReservationDTO getReservationDetails(String reservation_id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            reservationDao.SetSession(session);
            ReservationEntity reservation = reservationDao.Get(reservation_id);
            session.close();
            return reservation.ToDto();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDao.SetSession(session);
            roomDao.SetSession(session);
            reservationDao.Update(reservationDTO.ToEntityUpdate());
            roomDao.Update(roomDTO.ToEntity());
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean DeleteReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDao.SetSession(session);
            roomDao.SetSession(session);
            reservationDao.Delete(reservationDTO.ToEntity());
            roomDao.Update(roomDTO.ToEntity());
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public StudentDTO GetStudentName(String ID) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            reservationDao.SetSession(session);
            List<StudentEntity> student =  reservationDao.GetStudentName(ID);
            StudentEntity studentEntity = student.get(0);
            session.close();
            return studentEntity.ToDto();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public RoomDTO GetKeyMoney(String ID) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            reservationDao.SetSession(session);
            List<RoomEntity> room =  reservationDao.GetKeyMoney(ID);
            RoomEntity roomEntity = room.get(0);
            session.close();
            return roomEntity.ToDto();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<ReservationEntity> getReservationDetails() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            reservationDao.SetSession(session);
            List<ReservationEntity>reservation = reservationDao.getReservationDetails();
            session.close();
            return reservation;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
