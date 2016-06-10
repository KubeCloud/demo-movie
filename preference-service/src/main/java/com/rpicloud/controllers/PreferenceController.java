package com.rpicloud.controllers;

import com.rpicloud.models.Preference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mixmox on 10/06/16.
 */

@RestController
public class PreferenceController {

    @RequestMapping("/preferences/{userId}")
    public ResponseEntity<Preference> movies(@PathVariable int userId) {

        if (userId == 1){
            return new ResponseEntity<Preference>(new Preference(1, "action"), HttpStatus.OK);
        }
        else if (userId == 2){
            return new ResponseEntity<Preference>(new Preference(2, "kids"), HttpStatus.OK);
        }

        return new ResponseEntity<Preference>(new Preference(0, "unknown"), HttpStatus.OK);
    }

}
