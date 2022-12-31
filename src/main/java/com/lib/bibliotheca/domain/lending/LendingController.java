package com.lib.bibliotheca.domain.lending;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/lending")
public class LendingController {

    @Resource
    private LendingService lendingService;

    @PostMapping("/new")
    @Operation(summary = "Adds a new lending")
    public void addLending(@RequestBody LendingRequest request) {
        lendingService.addLending(request);
    }

}
