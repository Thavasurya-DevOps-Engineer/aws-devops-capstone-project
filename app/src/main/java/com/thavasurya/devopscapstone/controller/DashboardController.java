package com.thavasurya.devopscapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        addCommonAttributes(model);
        return "dashboard/index";
    }

    @GetMapping("/aws")
    public String aws(Model model) {
        addCommonAttributes(model);
        return "aws/index";
    }

    @GetMapping("/docker")
    public String docker(Model model) {
        addCommonAttributes(model);
        return "docker/index";
    }

    @GetMapping("/jenkins")
    public String jenkins(Model model) {
        addCommonAttributes(model);
        return "jenkins/index";
    }

    @GetMapping("/monitoring")
    public String monitoring(Model model) {
        addCommonAttributes(model);
        return "monitoring/index";
    }

    @GetMapping("/deployments")
    public String deployments(Model model) {
        addCommonAttributes(model);
        return "deployments/index";
    }

    @GetMapping("/alerts")
    public String alerts(Model model) {
        addCommonAttributes(model);
        return "alerts/index";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        addCommonAttributes(model);
        return "settings/index";
    }

    private void addCommonAttributes(Model model) {
        model.addAttribute(
                "currentTime",
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
        );
    }
}