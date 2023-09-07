package lk.ijse.HostalMangement.bo.custom;

import lk.ijse.HostalMangement.bo.SuperBo;
import lk.ijse.HostalMangement.dto.ReservationDTO;
import lk.ijse.HostalMangement.dto.RoomDTO;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.entity.ReservationEntity;

import java.util.List;

public interface ReservationBo extends SuperBo {
    String SaveReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO);
    ReservationDTO getReservationDetails(String reservation_id);
    boolean UpdateReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO);
    boolean DeleteReservationDetails(ReservationDTO reservationDTO,RoomDTO roomDTO);
    StudentDTO GetStudentName(String ID);
    RoomDTO GetKeyMoney(String ID);
    List<ReservationEntity> getReservationDetails();
}
