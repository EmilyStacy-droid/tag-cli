package com.improving.tagcli.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OldSchoolDatabaseClient {
    private static final Logger logger = LoggerFactory.getLogger(OldSchoolDatabaseClient.class);

    public void readRecordFromDatabase() throws SQLException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            logger.info("connection made");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM weapon LIMIT 10");

            ResultSetMetaData metaData = resultSet.getMetaData();

            String columns = " ";
            for ( int i = 1; i <= metaData.getColumnCount(); i ++ ) {
                columns = columns + "  (" + i + ") " + metaData.getColumnName(i) + ",";
            }

            logger.info("+Table Columns:" + columns);

            resultSet.beforeFirst();

            while (resultSet.next()) {
               int id =  resultSet.getInt(1);
                String name = resultSet.getString(2);
                String area = resultSet.getString(17);
                logger.info("id:{}, Name{}, Area{}", id, name, area);
            }

            resultSet.close();
        }
        catch(SQLException e) {
            logger.error(e.getMessage(),e);
            //System.out.println(e.getErrorCode());
            //System.out.println(e.getSQLState());
        }
    }


    public void insertRecordIntoDataBase() throws SQLException {
        //referring to the driver in TagCli.
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            logger.info("connection made");

           long rowsAffected = statement.executeLargeUpdate("INSERT INTO weapon (Name, Area, itemType)" + "VALUES('Test Dagger','Test Area',' Weapon')");

           if(rowsAffected > 0) {
               logger.info("Committing....");
               connection.commit();
           }

            }
        catch(SQLException e) {
            logger.error(e.getMessage(),e);
            //System.out.println(e.getErrorCode());
            //System.out.println(e.getSQLState());
        }
        }
      private Connection getConnection() throws SQLException {
          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tag?serverTimezone=UTC","EmilyStacylocal","bootcamp2019Improving");
          connection.setAutoCommit(false);

          return connection;
      }
    }


