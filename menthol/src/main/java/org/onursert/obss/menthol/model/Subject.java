package org.onursert.obss.menthol.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<ProgramSubject> programSubjects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<SubSubject> subSubjects;

    @Column(name = "name")
    private String name;

    public Subject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProgramSubject> getProgramSubjects() {
        return programSubjects;
    }

    public void setProgramSubjects(List<ProgramSubject> programSubjects) {
        this.programSubjects = programSubjects;
    }

    public List<SubSubject> getSubSubjects() {
        return subSubjects;
    }

    public void setSubSubjects(List<SubSubject> subSubjects) {
        this.subSubjects = subSubjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
