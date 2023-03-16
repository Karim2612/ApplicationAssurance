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
import com.insurance.model.Coverage;
import com.insurance.repository.CoverageRepository;


@Controller
public class CoverageController {

	@Autowired
	private CoverageRepository coverageRepository;
	
	
	@GetMapping("/coverages")
	public String showCoverageList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/coverages/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Coverage> page = coverageRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listCoverages", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "coverages";
	}
	
	@GetMapping("/coverages/new")
	public String showCreateNewCoverageForm(Model model) {
		model.addAttribute("coverage", new Coverage());
		return "coverage_form";
	}

	
	@PostMapping("/coverages/save")
	public String saveCoverage(Coverage coverage) {
		coverageRepository.save(coverage);
		return "redirect:/coverages";
	}

	@GetMapping("/coverages/edit/{id}")
	public String showCreateNewCoverageForm(@PathVariable Integer id, Model model) {
		model.addAttribute("coverage", coverageRepository.findById(id).get());
		return "coverage_form";
	}
	
	@GetMapping("/coverages/delete/{id}")
	public String deleteCoverage(@PathVariable Integer id) {
		coverageRepository.deleteById(id);
		return "redirect:/coverages";
	}
}
