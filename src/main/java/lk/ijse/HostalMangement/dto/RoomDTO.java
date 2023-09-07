package lk.ijse.HostalMangement.dto;

import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomDTO {

    private String RoomTypeId;
    private String Type;
    private String KeyMoney;
    private int Qty;
    //private List<ReservationEntity> reservationEntities = new ArrayList<>();

    public RoomEntity ToEntity(){
        RoomEntity room = new RoomEntity();
        room.setRoomTypeId(this.RoomTypeId);
        room.setType(this.Type);
        room.setKeyMoney(this.KeyMoney);
        room.setQty(this.Qty);
        //room.setReservationEntities(this.reservationEntities);
        return room;
    }

}
