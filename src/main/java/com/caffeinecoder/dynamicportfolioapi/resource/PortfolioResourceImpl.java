package com.caffeinecoder.dynamicportfolioapi.resource;

import com.caffeinecoder.dynamicportfolioapi.dataaccess.PortfolioDataAccess;
import com.caffeinecoder.dynamicportfolioapi.model.Login;
import com.caffeinecoder.dynamicportfolioapi.model.UserSignUp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.Map;


@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioResourceImpl implements PortfolioResource{
    PortfolioDataAccess portfolioDataAccess = new PortfolioDataAccess();
    public PortfolioResourceImpl() throws SQLException {
    }

    @Override
    public Map<String, String> signUp(UserSignUp userSignUp) throws SQLException {
        Map<String,String> response;
        portfolioDataAccess = new PortfolioDataAccess();
        response = portfolioDataAccess.signUp(userSignUp);
        return response;
    }

    @Override
    public Map<String, String> login(Login login) throws SQLException {
        Map<String,String> response;
        portfolioDataAccess = new PortfolioDataAccess();
        response = portfolioDataAccess.verifyLogin(login);
        return response;
    }

}
