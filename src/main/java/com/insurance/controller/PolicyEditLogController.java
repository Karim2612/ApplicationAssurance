package com.insurance.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.insurance.model.PolicyEditLog;
import com.insurance.repository.PolicyEditLogRepository;
import com.insurance.repository.PolicyRepository;

@Controller
public class PolicyEditLogController {

	@Autowired
	private PolicyEditLogRepository pEditLogRepository;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@GetMapping("/policyEditLogs")
	public String listPolicyEditLogs(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/policyEditLogs/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<PolicyEditLog> page = pEditLogRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listPolicyEditLogs", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "policyEditLogs";
	}
	
	@GetMapping("/policyEditLogs/new")
	public String showPolicyEditLogNewForm(Model model) {
		model.addAttribute("policyEditLog", new PolicyEditLog());
		model.addAttribute("listPolicies", policyRepository.findAll());
		return "policyEditLog_form";
	}
	
	@PostMapping("/policyEditLogs/save")
	public String savePolicyEditLog(PolicyEditLog policyEditLog, HttpServletRequest request) {
		int last = policyRepository.findAll().size();
		Policy p = policyRepository.findAll().get(last-1);
		policyEditLog.setPolicy(p);
		pEditLogRepository.save(policyEditLog);
		return "redirect:/policyEditLogs";
	}
	
	@GetMapping("/policyEditLogs/edit/{id}")
	public String showPolicyEditLogEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listPolicies", policyRepository.findAll());
		model.addAttribute("policyEditLog", pEditLogRepository.findById(id).get());
		return "policyEditLog_form";
	}
	
	@GetMapping("/policyEditLogs/delete/{id}")
	public String deletePolicyEditLog(@PathVariable Integer id, Model model) {
		pEditLogRepository.deleteById(id);
		return "redirect:/policyEditLogs";
	}
}