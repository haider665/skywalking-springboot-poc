package com.example.authservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mojib.haider
 * @since 5/8/24
 */
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

//    @Trace
    @GetMapping("/check")
    public ResponseEntity<String> check() {
        log.info("Auth Service check");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

//    @Trace
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("healthy", HttpStatus.OK);
    }
}
