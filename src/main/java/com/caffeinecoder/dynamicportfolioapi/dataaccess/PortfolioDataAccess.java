package com.caffeinecoder.dynamicportfolioapi.dataaccess;

import com.caffeinecoder.dynamicportfolioapi.model.Login;
import com.caffeinecoder.dynamicportfolioapi.model.UserSignUp;
import com.caffeinecoder.dynamicportfolioapi.utilities.PasswordUtility;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class PortfolioDataAccess {
    private PreparedStatement preparedStatement;
    private final Connection connection;

    private static final String VERIFY_EMAIL_ID = "Select * from users where emailId = ?";

    private static final String VERIFY_LOGIN = "Select * from users where emailId = ?";
    private static final String INSERT_INTO_USERS = " insert into users (emailId, firstName, lastName, password)"
            + " values (?, ?, ?, ?)";

    PasswordUtility passwordUtility = new PasswordUtility();

    public PortfolioDataAccess() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dynamicportfolio", "root", "admin");
    }

    public Map<String, String> signUp(UserSignUp userSignUp) throws SQLException {
        Map<String, String> responseMessage = new HashMap<>();
        boolean isValidEmail = false;
        isValidEmail = isValidEmailId(userSignUp.getEmailId());
        if (isValidEmail) {
            if (emailAlreadyExist(userSignUp.getEmailId())) {
                responseMessage.put("responseCode", "400");
                responseMessage.put("message", "Email Already Exist");
                return responseMessage;
            } else {

                String encryptedPassword = passwordUtility.hashPassword(userSignUp.getPassword());
                try {
                    int response = 0;
                    preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
                    preparedStatement.setString(1, userSignUp.getEmailId());
                    preparedStatement.setString(2, userSignUp.getFirstName());
                    preparedStatement.setString(3, userSignUp.getLastName());
                    preparedStatement.setString(4, encryptedPassword);
                    response = preparedStatement.executeUpdate();
                    if (response == 1) {
                        responseMessage.put("responseCode", "200");
                        responseMessage.put("message", "User Registered Successfully");
                        return responseMessage;
                    }

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    // printStackTrace method
                    // prints line numbers + call stack
                    e.printStackTrace();
                    // Prints what exception has been thrown
                    System.out.println(e);
                }
            }
        }
        responseMessage.put("responseCode", "400");
        responseMessage.put("message", "Invalid Email Id, please check and retry again");
        return responseMessage;
    }

    private boolean emailAlreadyExist(String emailId) {
        try {
            preparedStatement = connection.prepareStatement(VERIFY_EMAIL_ID);
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            // printStackTrace method
            // prints line numbers + call stack
            e.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(e);
        }
        return true;
    }

    public boolean isValidEmailId(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();

    }

    public Map<String,String> verifyLogin(Login login) {
        Map<String, String> response = new HashMap<>();
        boolean validPassword;
        if(!isValidEmailId(login.getEmailId())){
            response.put("400", "Invalid Email Id, please check and retry again");
            return response;
        }
        else {
            if (!emailAlreadyExist(login.getEmailId())) {
                response.put("responseCode", "404");
                response.put("message", "Please sign up to login");
                return response;
            }
            else {
                try {
                    preparedStatement = connection.prepareStatement(VERIFY_LOGIN);
                    preparedStatement.setString(1, login.getEmailId());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        String storedPassword = resultSet.getString("password");
                        validPassword = passwordUtility.checkPassword(login.getPassword(),storedPassword);
                        if(validPassword)
                        {
                            response.put("responseCode", "200");
                            response.put("firstName", resultSet.getString("firstName"));
                            response.put("lastName", resultSet.getString("lastName"));
                            response.put("emailId", resultSet.getString("emailId"));
                            return response;
                        }
                        else {
                            response.put("responseCode","401");
                            response.put("message","Invalid credentials, please try again");
                        }
                    }
                    return response;
                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    // printStackTrace method
                    // prints line numbers + call stack
                    e.printStackTrace();
                    // Prints what exception has been thrown
                    System.out.println(e);
                }
            }
        }
        return null;
    }
}
