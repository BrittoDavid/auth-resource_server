/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.lucasian.auth.britto.cloud.controller;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DavidBritto
 */

@RestController
@RequestMapping(path = "/loan")
public class LoanController {
    
    @GetMapping
    public Map<String, String> loan(){
        return Collections.singletonMap("msj", "Loan");
    }
    
}
