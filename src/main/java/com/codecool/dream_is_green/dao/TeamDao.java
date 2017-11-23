package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.ArtifactCategoryModel;
import com.codecool.dream_is_green.model.ArtifactModel;
import com.codecool.dream_is_green.model.StudentModel;
import com.codecool.dream_is_green.model.TeamShoppingModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TeamDao extends AbstractDAO<TeamShoppingModel> {
    public void loadDataAboutTeam(Integer teamId) {

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();
            String query;
            if (checkFieldArtifact(teamId)) {
                query = String.format("SELECT * FROM TeamsTable join StudentsTable on TeamsTable.team_id = StudentsTable.team_id  " +
                        "join UsersTable on StudentsTable.user_id = UsersTable.user_id where TeamsTable.team_id == %d;", teamId);
            } else {
                query = String.format("SELECT * FROM TeamsTable join StudentsTable on TeamsTable.team_id = StudentsTable.team_id  " +
                        "join UsersTable on StudentsTable.user_id = UsersTable.user_id join ArtifactsTable on TeamsTable.artifact_id =" +
                        " ArtifactsTable.artifact_name where TeamsTable.team_id == %d;", teamId);
            }
            ResultSet result = stat.executeQuery(query);
            List<StudentModel> students = new LinkedList<>();
            ArtifactModel artifactModel = new ArtifactModel();
            TeamShoppingModel teamShoppingModel = new TeamShoppingModel();
            String name, surname, email, login, password, className, title, artifactCategory, teamName, votes;
            int userID, studentExp, price, state;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                studentExp = result.getInt("experience");
                state = result.getInt("state");
                if (!checkFieldArtifact(teamId)) {
                    title = result.getString("artifact_name");
                    price = result.getInt("price");
                    artifactCategory = result.getString("artifact_category");
                    ArtifactCategoryModel artifactCategoryModel = new ArtifactCategoryModel(artifactCategory);
                    artifactModel = new ArtifactModel(title, price, artifactCategoryModel);
                } else {
                    artifactModel = new ArtifactModel();
                }

                teamName = result.getString("team_name");
                votes = result.getString("votes");
                StudentModel student = new StudentModel(userID, name, surname, email,
                        login, password, className, studentExp);
                students.add(student);
                if (votes == null) {
                    if (checkFieldArtifact(teamId)) {
                        teamShoppingModel = new TeamShoppingModel(students, artifactModel, teamId, teamName, state);
                    } else {
                        teamShoppingModel = new TeamShoppingModel(students, artifactModel, teamId, teamName, state);
                    }

                } else {

                    teamShoppingModel = new TeamShoppingModel(students, artifactModel, teamId, teamName, votes, state);
                }
            }
            this.addObject(teamShoppingModel);
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkFieldArtifact(Integer teamId) {
        boolean empty = true;
        try {

            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();

            String query = String.format("SELECT artifact_name FROM TeamsTable join ArtifactsTable on TeamsTable.artifact_id =" +
                    " ArtifactsTable.artifact_name where TeamsTable.team_id == %d;", teamId);
            ResultSet result = stat.executeQuery(query);
            String name;
            while(result.next()) {
                name = result.getString("artifact_name");
                if (name == null) {
                    empty = true;
                } else {
                    empty = false;
                }
            }

            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return empty;
    }

}
