package com.caffeinecoder.dynamicportfolioapi.resource;

import com.caffeinecoder.dynamicportfolioapi.model.Login;
import com.caffeinecoder.dynamicportfolioapi.model.UserSignUp;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.Map;

public interface PortfolioResource {

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Map<String,String> signUp(@RequestBody UserSignUp userSignUp) throws SQLException;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,String> login(@RequestBody Login login) throws SQLException;

}
