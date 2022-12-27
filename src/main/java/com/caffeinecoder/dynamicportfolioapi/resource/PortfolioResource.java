package com.caffeinecoder.dynamicportfolioapi.resource;

import com.caffeinecoder.dynamicportfolioapi.model.Persons;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

public interface PortfolioResource {

    @RequestMapping(value="/getallpersons", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAllPersons() throws JSONException, SQLException;

    @RequestMapping(value = "/addPerson",method = RequestMethod.POST)
    public int addPerson(@RequestBody Persons persons) throws SQLException;
}
