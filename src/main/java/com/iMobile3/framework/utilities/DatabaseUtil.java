package com.iMobile3.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {


    //Open connectivity
    public static void Open(String connectionString) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.getConnection(connectionString);
        } catch (Exception e) {

        }

    }

    public static void Close()
        {
            //
        }


     public static void ExecuteQuery(String query, Connection connection)
     {
         Statement statement = null;
         try
         {
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
         }
         catch (Exception e)
         {
            // LogUtil.Write
         }

     }

    //Query

}
