package model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "requests")
public class Requests {
    private Long id;
    private Date requestTime;
    private String fullName;
    private Long birthYear;
    private String gender;

    public Requests(long id, java.sql.Date request_date_time, String full_name, long birth_year, String gender){}

    public Requests(Date requestTime, String fullName, Long birthYear, String gender) {
        this.requestTime = requestTime;
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public Requests(String fullName, Long birthYear, String gender) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public Requests(long id, String full_name, long birth_year, String gender) {

    }

    public Requests() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Long birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.fullName + " " + this.birthYear + " " + this.gender;
    }
}
