package pbo.f01.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 12S22030 - Bryan Evans Simamora
 * 12S22036 - Jonathan Fransilo Hutabarat
 */
@Entity
@Table(name = "dorms")
public class Dorm {

    @Id
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "gender", nullable = false, length = 8)
    private String gender;

    @OneToMany(mappedBy = "dorm", targetEntity = Student.class, cascade = CascadeType.ALL)
    private Set<Student> residents;

    public Dorm() {
        // empty
    }

    public Dorm(String name, Integer capacity, String gender) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getResidentNum() {
        return this.residents.size();
    }

    public boolean addResident(Student student) {
        if (!student.getGender().equals(this.gender)) {
            return false;
        }

        if (this.getResidentNum() >= this.capacity) {
            return false;
        }

        student.setDorm(null);

        return this.residents.add(student);
    }

    public Set<Student> getResidents() {
        Set<Student> sortedResidents = new TreeSet<Student>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        sortedResidents.addAll(residents);

        return sortedResidents;
    }

    public void setResidents(Set<Student> residents) {
        this.residents = residents;
    }

    public boolean hasSpace() {
        return this.capacity > this.getResidentNum();
    }

    public boolean isAvailableFor(Student student) {
        if (this.hasSpace()) {
            return false;
        }

        if (!this.gender.equals(student.getGender())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name + "|" + gender + "|" + capacity + "|" + getResidentNum();
    }
}