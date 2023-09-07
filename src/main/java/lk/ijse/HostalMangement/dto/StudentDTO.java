package lk.ijse.HostalMangement.dto;

import lk.ijse.HostalMangement.embedded.Name;
import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {

    private String StudentId;
    private String Name;
    private String LastName;
    private Name FullName;
    private String Address;
    private String ContactNumber;
    private String DateOfBirth;
    private String Gender;
    //private List<ReservationEntity> reservationEntities = new ArrayList<>();

    public StudentEntity ToEntity(){
        StudentEntity student =  new StudentEntity();
        student.setStudentId(this.StudentId);
        student.setFullName(this.FullName);
        student.setAddress(this.Address);
        student.setContactNumber(this.ContactNumber);
        student.setDateOfBirth(this.DateOfBirth);
        student.setGender(this.Gender);
        //student.setReservationEntities(this.reservationEntities);
        return student;
    }

}
