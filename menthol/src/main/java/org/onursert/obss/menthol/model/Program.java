package org.onursert.obss.menthol.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "program", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProgramSubject> programSubjects;

    @OneToMany(mappedBy = "program", cascade = CascadeType.REMOVE)
    private List<Phase> phases;

    @Column(name = "mentorUid")
    private String mentorUid;

    @Column(name = "menteeUid")
    private String menteeUid;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private Integer startDate;

    @Column(name = "accepted", columnDefinition = "boolean default false")
    private Boolean accepted;

    @Column(name = "requestAccept", columnDefinition = "boolean default false")
    private Boolean requestAccept;

    @Column(name = "ended", columnDefinition = "boolean default false")
    private Boolean ended;

    public Program() {
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

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public String getMentorUid() {
        return mentorUid;
    }

    public void setMentorUid(String mentorUid) {
        this.mentorUid = mentorUid;
    }

    public String getMenteeUid() {
        return menteeUid;
    }

    public void setMenteeUid(String menteeUid) {
        this.menteeUid = menteeUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getRequestAccept() {
        return requestAccept;
    }

    public void setRequestAccept(Boolean requestAccept) {
        this.requestAccept = requestAccept;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }
}
