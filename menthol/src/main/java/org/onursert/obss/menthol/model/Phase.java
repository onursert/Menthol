package org.onursert.obss.menthol.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "phase")
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "program_id", referencedColumnName = "id", nullable = false)
    private Program program;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private Integer startDate;

    @Column(name = "endDate")
    private Integer endDate;

    // Given by mentor
    @Column(name = "mentorComment")
    private String mentorComment;
    @Column(name = "mentorPoint")
    private Integer mentorPoint;

    // Given by mentee
    @Column(name = "menteeComment")
    private String menteeComment;
    @Column(name = "menteePoint")
    private Integer menteePoint;

    @Column(name = "ended", columnDefinition = "boolean default false")
    private Boolean ended;

    @Column(name = "started", columnDefinition = "boolean default false")
    private Boolean started;

    public Phase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getMentorComment() {
        return mentorComment;
    }

    public void setMentorComment(String mentorComment) {
        this.mentorComment = mentorComment;
    }

    public Integer getMentorPoint() {
        return mentorPoint;
    }

    public void setMentorPoint(Integer mentorPoint) {
        this.mentorPoint = mentorPoint;
    }

    public String getMenteeComment() {
        return menteeComment;
    }

    public void setMenteeComment(String menteeComment) {
        this.menteeComment = menteeComment;
    }

    public Integer getMenteePoint() {
        return menteePoint;
    }

    public void setMenteePoint(Integer menteePoint) {
        this.menteePoint = menteePoint;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }
}
