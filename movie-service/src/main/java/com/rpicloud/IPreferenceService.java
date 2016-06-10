package com.rpicloud;

import java.util.List;

import com.rpicloud.models.Preference;
import feign.Param;
import feign.RequestLine;

/**
 * Created by mixmox on 10/06/16.
 */
public interface IPreferenceService {
    @RequestLine("GET /preferences/{userId}")
    Preference preferences(@Param("userId") int userId);
}
