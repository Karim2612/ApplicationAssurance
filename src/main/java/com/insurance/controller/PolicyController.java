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
import com.insurance.model.Policy;
import com.insurance.repository.CoverageRepository;
import com.insurance.repository.PolicyRepository;

@Controller
public class PolicyController {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@GetMapping("/policies")
	public String showPolicyList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/policies/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Policy> page = policyRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listPolicies", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "policies";
	}
	
	@GetMapping("/policies/new")
	public String showCreateNewPolicyForm(Model model) {
		model.addAttribute("listCoverages", coverageRepository.findAll());
		model.addAttribute("policy", new Policy());
		return "policy_form";
	}
	
	@PostMapping("/policies/save")
	public String savePolicy(Policy policy) {
		policyRepository.save(policy);
		return "redirect:/policies";
	}
	
	@GetMapping("/policies/edit/{id}")
	public String showCreateNewPolicyForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listCoverages", coverageRepository.findAll());
		model.addAttribute("policy", policyRepository.findById(id).get());
		return "policy_form";
	}
	
	@GetMapping("/policies/delete/{id}")
	public String deletePolicy(@PathVariable Integer id) {
		policyRepository.deleteById(id);
		return "redirect:/policies";
	}
}
