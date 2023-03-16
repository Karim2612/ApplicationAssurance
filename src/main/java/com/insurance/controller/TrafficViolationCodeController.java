package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.insurance.Constants;
import com.insurance.model.TrafficViolationCode;
import com.insurance.repository.DriverRepository;
import com.insurance.repository.TrafficViolationCodeRepository;

@Controller
public class TrafficViolationCodeController {

	@Autowired
	private TrafficViolationCodeRepository trafficViolationCodeRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@GetMapping("/trafficViolationCodes")
	public String showTrafficViolationCodeList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/trafficViolationCodes/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<TrafficViolationCode> page = trafficViolationCodeRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listTrafficViolationCodes", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "trafficViolationCodes";
	}
	
	@GetMapping("/trafficViolationCodes/new")
	public String showCreateNewTrafficViolationCodeForm(Model model) {
		model.addAttribute("trafficViolationCode", new TrafficViolationCode());
		return "trafficViolationCode_form";
	}

	
	@PostMapping("/trafficViolationCodes/save")
	public String saveTrafficViolationCode(TrafficViolationCode trafficViolationCode) {
		trafficViolationCodeRepository.save(trafficViolationCode);
		return "redirect:/trafficViolationCodes";
	}

	@GetMapping("/trafficViolationCodes/edit/{id}")
	public String showCreateNewTrafficViolationCodeForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listDrivers", driverRepository.findAll());
		model.addAttribute("trafficViolationCode", trafficViolationCodeRepository.findById(id).get());
		return "trafficViolationCode_form";
	}
	
	@GetMapping("/trafficViolationCodes/delete/{id}")
	public String deleteTrafficViolationCode(@PathVariable Integer id) {
		trafficViolationCodeRepository.deleteById(id);
		return "redirect:/trafficViolationCodes";
	}
}
