package com.caffeinecoder.dynamicportfolioapi.dataaccess;

import com.caffeinecoder.dynamicportfolioapi.model.Persons;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;


public class PortfolioDataAccess {
    private PreparedStatement preparedStatement;
    private final Connection connection;
    private JSONArray jsonArray;
    private static final String FETCH_ALL_PERSONS = "Select * from Persons";

    private static final String INSERT_INTO_PERSONS = " insert into persons (PersonID, LastName, FirstName, Address, City)"
            + " values (?, ?, ?, ?, ?)";

    public PortfolioDataAccess() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dynamicportfolio", "root", "admin");
        jsonArray = null;
    }

    public JSONArray fetchAllPersons() throws SQLException, JSONException {
        preparedStatement = connection.prepareStatement(FETCH_ALL_PERSONS);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        JSONArray jsonArray1 = new JSONArray();
        ResultSetMetaData resultSetMetaData1 = resultSet1.getMetaData();
        while (resultSet1.next()) {
            int numColumns = resultSetMetaData1.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= numColumns; i++) {
                String column_name = resultSetMetaData1.getColumnName(i);
                obj.put(column_name, resultSet1.getObject(column_name));
            }
            jsonArray1.put(obj);

        }
        System.out.println(jsonArray1);
        return jsonArray1;
    }

    public int addPerson(Persons persons) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_PERSONS);
            preparedStatement.setInt(1, persons.getPersonId());
            preparedStatement.setString(2, persons.getLastName());
            preparedStatement.setString(3, persons.getFirstName());
            preparedStatement.setString(4, persons.getAddress());
            preparedStatement.setString(5, persons.getCity());
            return preparedStatement.executeUpdate();
        }
        catch (Exception e){
            System.err.println("Got an exception!");
            // printStackTrace method
            // prints line numbers + call stack
            e.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(e);
        }

        return 0;
    }
}
