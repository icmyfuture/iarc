package cn.icmyfuture.iarc.zzz.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @CrossOrigin(origins = "*")
    @GetMapping("index")
    public int pubG() {
        return 4004;
    }
}
