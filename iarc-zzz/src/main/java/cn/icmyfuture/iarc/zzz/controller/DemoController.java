package cn.icmyfuture.iarc.zzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    private static final String pubGCounterName = "iarc-zzz.demo_index.count";

    @Autowired
    private CounterService counterService;

    @CrossOrigin(origins = "*")
    @GetMapping("index")
    public int pubG() {
        counterService.increment(pubGCounterName);
        return 4004;
    }
}
