package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
            String name, surname, email, login, password, className, title, artifactCategory, teamName, voted;
            int userID, studentExp, price, state, votes;

            while(result.next()) {

                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                password = result.getString("password");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                voted = result.getString("voted");
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
                votes = result.getInt("votes");
                StudentModel student = new StudentModel(userID, name, surname, email,
                        login, password, className, studentExp);
                student.setVoted(voted);
                student.setTeamId(teamId);
                students.add(student);
                if (votes == 0) {
                    if (checkFieldArtifact(teamId)) {
                        teamShoppingModel = new TeamShoppingModel(students, artifactModel, teamId, teamName, votes, state);
                    } else {
                        teamShoppingModel = new TeamShoppingModel(students, artifactModel, teamId, teamName, votes, state);
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
    public void loadTeams() {

        try {
            LinkedList<String> temporaryTeamName = new LinkedList<>();
            Connection conn = DatabaseConnection.getConnection();
            Statement stat = conn.createStatement();
            String query = String.format("SELECT * FROM TeamsTable join StudentsTable on TeamsTable.team_id = StudentsTable.team_id  " +
                       "join UsersTable on StudentsTable.user_id = UsersTable.user_id;");
            ResultSet result = stat.executeQuery(query);
            LinkedList<StudentModel> students = new LinkedList<>();
            String name, surname, email, login, className, teamName, password;
            int userID, studentExp, teamId;
            LinkedList<TeamShoppingModel> temporaryTeams = new LinkedList<>();

            while(result.next()) {

                TeamShoppingModel teamShoppingModel = new TeamShoppingModel();
                name = result.getString("name");
                surname = result.getString("surname");
                email = result.getString("email");
                login = result.getString("login");
                teamName = result.getString("team_name");
                userID = result.getInt("user_id");
                className = result.getString("class_name");
                studentExp = result.getInt("experience");
                teamId = result.getInt("team_id");
                password = result.getString("password");
                teamShoppingModel.setNameTeam(teamName);
                teamShoppingModel.setTeamId(teamId);
                StudentModel student = new StudentModel(userID, name, surname, email,
                        login, password, className, studentExp);
                student.setTeamId(teamId);
                temporaryTeamName.add(teamName);

                if (checkElementInList(temporaryTeamName, teamName) == true) {
                    students = new LinkedList<>();
                    students.add(student);
                } else {
                    students.add(student);
                }
                teamShoppingModel.setStudentModels(students);
                temporaryTeams.add(teamShoppingModel);
            }
            addToMainList(temporaryTeams);
            result.close();
            stat.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDataAboutTeam(Integer teamId, String column, String content) {

        Connection connection;

        try {
            String statement = "";
            connection = DatabaseConnection.getConnection();
            if (column.equals("team_name")) {
                statement = "UPDATE TeamsTable SET team_name = ? WHERE team_id == ?;";
            } else if (column.equals("artifact_id")) {
                statement = "UPDATE TeamsTable SET artifact_id = ? WHERE team_id == ?;";
            } else if (column.equals("votes")) {
                statement = "UPDATE TeamsTable SET votes = ? WHERE team_id == ?;";
            } else if (column.equals("state")) {
                statement = "UPDATE TeamsTable SET state = ? WHERE team_id == ?;";
            }
            PreparedStatement prepStmt = connection.prepareStatement(statement);

            if (column.equals("state") || column.equals("votes")) {
                prepStmt.setInt(1, Integer.valueOf(content));
            } else {
                prepStmt.setString(1, content);
            }
            prepStmt.setInt(2, teamId);
            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTeamId(TeamShoppingModel teamShoppingModel) {

        Connection connection;
        Statement statement;
        int teamID = 0;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            String query = "SELECT team_id FROM TeamsTable WHERE team_name = '" + teamShoppingModel.getNameTeam() + "';";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                teamID = result.getInt("team_id");
            }

            result.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamID;
    }

    public void insertNewTeam(TeamShoppingModel teamShoppingModel) {

        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            String statement = "INSERT INTO TeamsTable (team_name)\n" +
                    "VALUES (?);";
            PreparedStatement prepStmt = connection.prepareStatement(statement);
            prepStmt.setString(1, teamShoppingModel.getNameTeam());
            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAllRecordsFromTeamstable(MentorModel mentorModel) {

        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepStmt;
            String statement = "DELETE FROM TeamsTable WHERE TeamsTable.team_id IN\n" +
                               "(SELECT team_id FROM StudentsTable WHERE class_name =?)";
            System.out.println(mentorModel.getClassName());
            prepStmt = connection.prepareStatement(statement);
            prepStmt.setString(1, mentorModel.getClassName());

            prepStmt.executeUpdate();
            connection.commit();
            prepStmt.close();

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

    private boolean checkElementInList(LinkedList<String> temps, String nameTeam) {
        boolean isInList = false;
        if (temps.size() > 1) {
            if (temps.get(temps.size()-2).equals(nameTeam)) {
                isInList = false;
            } else {
                isInList = true;
            }
        } else {
            isInList = false;
        }
        return isInList;
    }

    private void addToMainList(LinkedList<TeamShoppingModel> teams) {
        int i = 0;
        TeamShoppingModel tempModel = new TeamShoppingModel();
        System.out.println(teams.size()+"addToMainLis");
        for (TeamShoppingModel teamShoppingModel : teams) {
            if (i >= 1) {
                tempModel = teams.get(i-1);
            }
            if (!teamShoppingModel.getNameTeam().equals(tempModel.getNameTeam())) {
                if (i >= 1) {
                    this.getObjectList().add(tempModel);
                } else {
                    this.getObjectList().add(teamShoppingModel);
                }
            }
            i++;
        }
    }

}
