package lk.ijse.HostalMangement.dao;

import lk.ijse.HostalMangement.dao.custom.impl.ReservationDaoImpl;
import lk.ijse.HostalMangement.dao.custom.impl.RoomDaoImpl;
import lk.ijse.HostalMangement.dao.custom.impl.StudentDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getDaoFactory(){
        return (daoFactory==null) ? daoFactory = new DaoFactory() : daoFactory;
    }

    public enum DaoType {
        STUDENT,ROOM,RESERVATION
    }

    public <SuperDao> SuperDao getDao(DaoType daoType){
        switch (daoType){
            case STUDENT:
                return (SuperDao) new StudentDaoImpl();
            case ROOM:
                return (SuperDao) new RoomDaoImpl();
            case RESERVATION:
                return (SuperDao) new ReservationDaoImpl();
            default:
                return null;
        }
    }

}
