package lk.ijse.HostalMangement.bo.custom;

import lk.ijse.HostalMangement.bo.SuperBo;
import lk.ijse.HostalMangement.dto.RoomDTO;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.entity.RoomEntity;

import java.util.List;

public interface RoomBo extends SuperBo {
    String SaveRoom(RoomDTO roomDTO);
    RoomDTO getRoom(String room_type_id);
    boolean UpdateRoom(RoomDTO roomDTO);
    boolean DeleteRoom(RoomDTO roomDTO);
    List<String> getAllRoomType();
    List<String> getAllRoomTypeID();
    List<RoomEntity> getRoomDetails();
}
