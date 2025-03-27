package com.CourseManagementAPI.dto;

import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

    @NotEmpty(message = "Firstname boş olmamalidir!")
    private String firstName;

    @Size(min = 3 , max = 30)
    private String lastName;

    private Date birthOfDate;

    @Email(message = "Email formatında bir adres girin!")
    private String email;

    public DtoStudentIU(String firstName,String lastName){
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public DtoStudentIU(String firstName,String lastName,String email){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DtoStudentIU that = (DtoStudentIU) obj;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
