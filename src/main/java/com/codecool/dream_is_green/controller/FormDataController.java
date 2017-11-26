package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.dao.StudentDAO;
import com.codecool.dream_is_green.dao.TeamDao;
import com.codecool.dream_is_green.model.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class FormDataController<T> {

    public T parseFormData(String formData, String objectType) {
        @SuppressWarnings("unchecked")
        List<String> data = dataProcessing(formData);
        T object = chooseObject(data, objectType);
        return object;
    }

    public T parseFormData(String formData, String objectType, Integer teamId, Integer userId) {
        @SuppressWarnings("unchecked")
        List<String> data = dataProcessing(formData);
        T object = chooseActionTeam(data, objectType, teamId, userId);
        return object;
    }
    private T chooseObject(List<String> data, String objectType) {
        if (objectType.equals("class")) {
            ClassModel classModel = new ClassModel(data.get(0));
            return (T)classModel;

        } else if (objectType.equals("level")) {
            LevelModel level = new LevelModel(data.get(0), Integer.valueOf(data.get(1)));
            return (T)level;

        } else if (objectType.equals("preUser")) {
            PreUserModel preUser = new PreUserModel(data.get(0), data.get(1),
                    data.get(2), data.get(3), data.get(4), data.get(5));
            return (T)preUser;
        } else if (objectType.equals("mail")) {
            PreMailModel preMail = new PreMailModel(data.get(3), data.get(2),
                    Integer.valueOf(data.get(1)), Integer.valueOf(data.get(0)));
            return (T) preMail;
        }
        return null;
    }

    private T chooseActionTeam(List<String> data, String objectType, Integer teamId, Integer userId) {
        TeamDao teamDao = new TeamDao();
        teamDao.loadDataAboutTeam(teamId);
        TeamShoppingModel teamShoppingModel = teamDao.getObjectList().get(0);
        Integer idSendMail = teamShoppingModel.getStudentModels().get(0).getUserID();
        if (objectType.equals("voteNo")) {
            PreMailModel preMailModel = new PreMailModel(data.get(1), "cancel group purchase", userId, idSendMail);
            return (T) preMailModel;

        } else if (objectType.equals("voteYes")) {
            Integer votes = teamShoppingModel.getVotes();
            votes += Integer.valueOf(data.get(0));
            teamShoppingModel.setVotes(votes);
            return (T) teamShoppingModel;

        } else if (objectType.equals("mark")) {
            teamShoppingModel.getArtifactModel().setTitle(null);
            teamShoppingModel.setState(Integer.valueOf(data.get(0)));
            teamShoppingModel.setVotes(Integer.valueOf(data.get(0)));
            return (T) teamShoppingModel;
        }
        return null;
    }

    public URIModel parseURI (String uri) {
        String[] pairs = uri.split("/");
        URIModel uriModel = new URIModel();

        if (pairs.length == 3) {
            uriModel = new URIModel(pairs[2]);
        }
        return uriModel;
    }

    private List<String> dataProcessing(String formData) {
        List<String> data = new ArrayList<>();
        String[] pairs = formData.split("&");

        try {
            for (String pair : pairs) {
                data.add(new URLDecoder().decode(pair.split("=")[1], "UTF-8"));
            }
        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }
        return data;
    }
}