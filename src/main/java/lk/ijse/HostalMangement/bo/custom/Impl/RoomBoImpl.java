package lk.ijse.HostalMangement.bo.custom.Impl;

import lk.ijse.HostalMangement.bo.custom.RoomBo;
import lk.ijse.HostalMangement.config.SessionFactoryConfig;
import lk.ijse.HostalMangement.dao.DaoFactory;
import lk.ijse.HostalMangement.dao.custom.RoomDao;
import lk.ijse.HostalMangement.dto.RoomDTO;
import lk.ijse.HostalMangement.entity.RoomEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomBoImpl implements RoomBo {

    RoomDao roomDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROOM);

    @Override
    public String SaveRoom(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomDao.SetSession(session);
            String save = roomDao.Save(roomDTO.ToEntity());
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
    public RoomDTO getRoom(String room_type_id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            roomDao.SetSession(session);
            RoomEntity roomEntity = roomDao.Get(room_type_id);
            session.close();
            return roomEntity.ToDto();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateRoom(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomDao.SetSession(session);
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
    public boolean DeleteRoom(RoomDTO roomDTO) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomDao.SetSession(session);
            roomDao.Delete(roomDTO.ToEntity());
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
    public List<String> getAllRoomType() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            roomDao.SetSession(session);
            List<String> type =  roomDao.getRoomType();
            session.close();
            return type;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<String> getAllRoomTypeID() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            roomDao.SetSession(session);
            List<String>typeId = roomDao.getRoomTypeID();
            session.close();
            return typeId;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<RoomEntity> getRoomDetails() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try{
            roomDao.SetSession(session);
            List<RoomEntity>room = roomDao.getRoomDetails();
            session.close();
            return room;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
