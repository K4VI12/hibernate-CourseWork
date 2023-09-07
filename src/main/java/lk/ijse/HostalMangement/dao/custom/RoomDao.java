package lk.ijse.HostalMangement.dao.custom;

import lk.ijse.HostalMangement.dao.CrudDao;
import lk.ijse.HostalMangement.dao.SuperDao;
import lk.ijse.HostalMangement.entity.RoomEntity;
import org.hibernate.Session;

import java.util.List;

public interface RoomDao extends CrudDao<RoomEntity,String> {
    void SetSession(Session session);
    List<String> getRoomType();
    List<String> getRoomTypeID();
    List<RoomEntity> getRoomDetails();
}
