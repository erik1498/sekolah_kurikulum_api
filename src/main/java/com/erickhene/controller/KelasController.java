package com.erickhene.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kelas/")
public class KelasController {
    @GetMapping
    public String getListKelas() {
        return "List Kelas";
    }
}
