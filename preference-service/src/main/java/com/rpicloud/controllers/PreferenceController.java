package com.rpicloud.controllers;

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
    public ResponseEntity<String> movies(@PathVariable int userId) {

        if (userId == 1){
            return new ResponseEntity<String>("action", HttpStatus.OK);
        }
        else if (userId == 2){
            return new ResponseEntity<String>("kids", HttpStatus.OK);
        }

        return new ResponseEntity<String>("unknown", HttpStatus.OK);
    }

}
