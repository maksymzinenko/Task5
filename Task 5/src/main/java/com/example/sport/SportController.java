package com.example.sport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SportController {

    private final ITournamentDAO dao;

    @Autowired
    // Щоб переключити на Oracle, зміни "mockDAO" на "oracleDAO"
    public SportController(@Qualifier("mockDAO") ITournamentDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tournaments", dao.getAll());
        model.addAttribute("newTournament", new Tournament());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Tournament tournament) {
        dao.add(tournament);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@RequestParam int year, Model model) {
        model.addAttribute("tournaments", dao.searchByYear(year));
        model.addAttribute("newTournament", new Tournament());
        return "index";
    }
}