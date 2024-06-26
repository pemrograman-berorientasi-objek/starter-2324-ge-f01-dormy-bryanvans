package pbo.f01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 12S22030 - Bryan Evans Simamora
 * 12S22036 - Jonathan Fransilo Hutabarat
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "entrance_year", nullable = false, length = 4)
    private String year;
    @Column(name = "gender", nullable = false, length = 8)
    private String gender;

    @ManyToOne(targetEntity = Dorm.class)
    @JoinColumn(name = "dorm_name")
    private Dorm dorm;

    public Student() {
        // empty
    }

    public Student(String id, String name, String year, String gender) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year;
    }

}