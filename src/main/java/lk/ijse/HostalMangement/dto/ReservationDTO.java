package lk.ijse.HostalMangement.dto;


import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {

    private String ReservationId;
    private String LastDate;
    private Timestamp OrderDateTime;
    private StudentEntity Student;
    private String StudentID;
    private RoomEntity Room;
    private String RoomTypeID;
    private String Status;
    private String StudentName;
    private String KeyMoney;

    public ReservationEntity ToEntity(){
        ReservationEntity reservation = new ReservationEntity();
        reservation.setReservationId(this.ReservationId);
        reservation.setLastDate(this.LastDate);
        reservation.setStudent(this.Student);
        reservation.setRoom(this.Room);
        reservation.setStatus(this.Status);
        reservation.setStudentName(this.StudentName);
        reservation.setKeyMoney(this.KeyMoney);
        return reservation;
    }

    public ReservationEntity ToEntityUpdate(){
        ReservationEntity reservation = new ReservationEntity();
        reservation.setReservationId(this.ReservationId);
        reservation.setLastDate(this.LastDate);
        reservation.setOrderDateTime(this.OrderDateTime);
        reservation.setStudent(this.Student);
        reservation.setRoom(this.Room);
        reservation.setStatus(this.Status);
        reservation.setStudentName(this.StudentName);
        reservation.setKeyMoney(this.KeyMoney);
        return reservation;
    }

}
