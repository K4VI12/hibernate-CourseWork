package lk.ijse.HostalMangement.dao.custom.impl;

import lk.ijse.HostalMangement.dao.custom.RoomDao;
import lk.ijse.HostalMangement.entity.RoomEntity;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private Session session;

    public RoomDaoImpl(){}

    @Override
    public String Save(RoomEntity roomEntity) {
        return (String) session.save(roomEntity);
    }

    @Override
    public RoomEntity Get(String id) {
        return session.get(RoomEntity.class,id);
    }

    @Override
    public void Update(RoomEntity roomEntity) {
        session.update(roomEntity);
    }

    @Override
    public void Delete(RoomEntity roomEntity) {
        session.delete(roomEntity);
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

    @Override
    public List<String> getRoomType() {
        String hql = "SELECT r.Type FROM RoomEntity r";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<String> getRoomTypeID() {
        String hql = "SELECT r.RoomTypeId FROM RoomEntity r";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RoomEntity> getRoomDetails() {
        String hql = "FROM RoomEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
