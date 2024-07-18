package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Services.LocationService;


@Controller
@RequestMapping("/view")

public class ViewController {

    @Autowired
    private LocationService locationService;
    
        @GetMapping("/new")
    public String showCreateForm(Model model, @ModelAttribute("location") LocationDTO location) {
        model.addAttribute("location", location);
        return "location-create";
    }

    @PostMapping
    @Transactional
    public String create(@ModelAttribute LocationDTO entity) {
        locationService.createLocation(entity);
        System.out.println(entity);
        return "redirect:/new";
    }

    @GetMapping("/{id}")
    public String getLocation(@PathVariable long id, Model model) {
        LocationDTO location = locationService.findById(id);
        model.addAttribute("location", location);
        return "location";
    }

    @GetMapping(value = "/{id}/avatar")
    @ResponseBody
    public byte[] getImage(@PathVariable long id) {
        return locationService.getImage(id).orElse(new byte[0]);
    }
}
