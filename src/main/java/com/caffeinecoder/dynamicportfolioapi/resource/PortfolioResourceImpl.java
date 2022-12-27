package com.caffeinecoder.dynamicportfolioapi.resource;

import com.caffeinecoder.dynamicportfolioapi.dataaccess.PortfolioDataAccess;
import com.caffeinecoder.dynamicportfolioapi.model.Persons;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioResourceImpl implements PortfolioResource {


    @RequestMapping(value="/getallpersons", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<String> getAllPersons() throws JSONException, SQLException {
        PortfolioDataAccess portfolioDataAccess = new PortfolioDataAccess();
        JSONArray json;
        json= portfolioDataAccess.fetchAllPersons();
        System.out.println(json);
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addPerson",method = RequestMethod.POST)
    @Override
    public int addPerson(@RequestBody Persons persons) throws SQLException {
        PortfolioDataAccess portfolioDataAccess = new PortfolioDataAccess();
        System.out.println("Patient Name "+ persons.toString());
        return  portfolioDataAccess.addPerson(persons);
    }

}
