package com.rab3tech.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rab3tech.coronavirustracker.model.LocationStats;
import com.rab3tech.coronavirustracker.service.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping({"/","home"})
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases=allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalCases",totalCases);
		model.addAttribute("totalNewCase",totalNewCases);
		
		return "home";
	}
	
	@GetMapping("/death")
	public String death(Model model) {
		List<LocationStats> deathSats = coronaVirusDataService.getDeathSat();
		int totalDeath=deathSats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewDeath = deathSats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("totalDeath",totalDeath);
		model.addAttribute("totalNewDeath",totalNewDeath);
		model.addAttribute("deathSats",deathSats);
		return "death";
	}
	
}
