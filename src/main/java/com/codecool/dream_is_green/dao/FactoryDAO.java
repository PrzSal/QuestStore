package com.codecool.dream_is_green.dao;

import com.codecool.dream_is_green.view.UIView;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FactoryDAO {

    UIView uiView = new UIView();

    public void checkIsDatabase() {
        File database = new File("quest_store.db");
        boolean exists = database.exists();

        if (!exists) {

            createDatabase(readSqlFile());
        }
    }

    public String readSqlFile() {

        String querySql = "";
        File fileSql = new File("quest_store.db.sql");

        try {
            Scanner readLine = new Scanner(fileSql);

            while (readLine.hasNext()) {

                querySql += readLine.next() + "\n";

            }
        } catch (FileNotFoundException e) {

            uiView.printMessage("File not found.");
        }

        return querySql;
    }
}
