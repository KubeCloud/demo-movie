package com.rpicloud;

import java.util.List;
import feign.RequestLine;

/**
 * Created by mixmox on 10/06/16.
 */
public interface IPreferenceService {
    @RequestLine("GET /preferences/")
    List<String> preferences();
}
