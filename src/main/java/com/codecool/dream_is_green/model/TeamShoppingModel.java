package com.codecool.dream_is_green.model;

import java.util.LinkedList;
import java.util.List;

public class TeamShoppingModel {
    List<StudentModel> studentModels = new LinkedList<>();
    ArtifactModel artifactModel;
    Integer teamId;
    String nameTeam;
    String vote;
    Integer state;

    public TeamShoppingModel() {
    }

    public TeamShoppingModel(List<StudentModel> studentModels, Integer teamId, String nameTeam, Integer state) {
        this.studentModels = studentModels;
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.state = state;
    }

    public TeamShoppingModel(List<StudentModel> studentModels, ArtifactModel artifactModel, Integer teamId, String nameTeam, String vote, Integer state) {
        this.studentModels = studentModels;
        this.artifactModel = artifactModel;
        this.teamId = teamId;
        this.nameTeam = nameTeam;
        this.vote = vote;
        this.state = state;
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

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
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
}