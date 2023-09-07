package lk.ijse.HostalMangement.entity;

import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.embedded.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @Column(name = "student_id", length = 40)
    private String StudentId;
    private Name FullName;
    @Column(name = "address",nullable = false,  columnDefinition = "TEXT" )
    private String Address;
    @Column(name = "contact_number",nullable = false, length = 50 )
    private String ContactNumber;
    @Column(name = "dob",nullable = false, length = 50 )
    private String DateOfBirth;
    @Column(name = "gender",nullable = false, length = 50 )
    private String Gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "Student")
    private List<ReservationEntity> reservationEntities = new ArrayList<>();

    public StudentEntity() {
    }

    public StudentEntity(String studentId, Name fullName, String address, String contactNumber, String dateOfBirth, String gender, List<ReservationEntity> reservationEntities) {
        StudentId = studentId;
        FullName = fullName;
        Address = address;
        ContactNumber = contactNumber;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        this.reservationEntities = reservationEntities;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public Name getFullName() {
        return FullName;
    }

    public void setFullName(Name fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public List<ReservationEntity> getReservationEntities() {
        return reservationEntities;
    }

    public void setReservationEntities(List<ReservationEntity> reservationEntities) {
        this.reservationEntities = reservationEntities;
    }

    public StudentDTO ToDto(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(this.StudentId);
        studentDTO.setFullName(this.FullName);
        studentDTO.setAddress(this.Address);
        studentDTO.setContactNumber(this.ContactNumber);
        studentDTO.setDateOfBirth(this.DateOfBirth);
        studentDTO.setGender(this.Gender);
        //studentDTO.setReservationEntities(this.reservationEntities);
        return studentDTO;
    }

}
