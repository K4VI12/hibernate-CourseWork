package lk.ijse.HostalMangement.dao;

public interface CrudDao <T,Type> extends SuperDao {

    Type Save(T t);
    T Get(Type type);
    void Update(T t);
    void Delete(T t);

}
