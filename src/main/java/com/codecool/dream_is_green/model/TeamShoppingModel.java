package com.codecool.dream_is_green.model;

import java.util.LinkedList;
import java.util.List;

public class TeamShoppingModel {
    List<StudentModel> studentModels = new LinkedList<>();
    ArtifactModel artifactModel;
    Integer teamId;
    String nameTeam;
    Integer votes;
    Integer state;

    public TeamShoppingModel() {
    }

    public TeamShoppingModel(List<StudentModel> studentModels, Integer teamId, String nameTeam, Integer state) {
        this.studentModels = studentModels;
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.state = state;
    }

    public TeamShoppingModel(List<StudentModel> studentModels, ArtifactModel artifactModel, Integer teamId, String nameTeam, Integer votes, Integer state) {
        this.studentModels = studentModels;
        this.artifactModel = artifactModel;
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.state = state;
        this.votes = votes;
    }

    public TeamShoppingModel(List<StudentModel> studentModels, ArtifactModel artifactModel, Integer teamId, String nameTeam, Integer state) {
        this.studentModels = studentModels;
        this.artifactModel = artifactModel;
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.state = state;
    }

    public List<StudentModel> getStudentModels() {
        return studentModels;
    }

    public ArtifactModel getArtifactModel() {
        return artifactModel;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer vote) {
        this.votes = vote;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public void setStudentModels(List<StudentModel> studentModels) {
        this.studentModels = studentModels;
    }
}