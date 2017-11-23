package com.codecool.dream_is_green.controller;

import com.codecool.dream_is_green.model.ClassModel;
import com.codecool.dream_is_green.model.LevelModel;
import com.codecool.dream_is_green.model.PreMailModel;
import com.codecool.dream_is_green.model.PreUserModel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class FormDataController<T> {

    public T parseFormData(String formData, String objectType) {
        @SuppressWarnings("unchecked")
        List<String> data = new ArrayList<>();
        String[] pairs = formData.split("&");

        try {
            for (String pair : pairs) {
                data.add(new URLDecoder().decode(pair.split("=")[1], "UTF-8"));
            }
        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }

        T object = chooseObject(data, objectType);
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
}