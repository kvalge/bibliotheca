package com.lib.bibliotheca.domain.lending;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/lending")
public class LendingController {

    @Resource
    private LendingService lendingService;

    @PostMapping("/new")
    @Operation(summary = "Adds a new lending")
    public void addLending(@RequestBody LendingRequest request) {
        lendingService.addLending(request);
    }

    @PutMapping("/return")
    @Operation(summary = "Updates lending on book return by library user id code (could be library card number and" +
            "by book name")
    public void updateOnReturn(@RequestParam String idCode, @RequestParam String bookName) {
        lendingService.updateOnReturn(idCode, bookName);
    }

    @GetMapping("/overdue")
    @Operation(summary = "Returns a list of lendings with the overdue return date")
    public List<LendingReturn> getOverdueLendingList() {
        return lendingService.getOverdueLendingList();
    }
}
