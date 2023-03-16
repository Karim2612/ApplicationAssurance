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
import com.insurance.model.Bill;
import com.insurance.repository.BillRepository;
import com.insurance.repository.PolicyRepository;

@Controller
public class BillController {

	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@GetMapping("/bills")
	public String listBills(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/bills/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Bill> page = billRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listBills", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "bills";
	}
	
	@GetMapping("/bills/new")
	public String showBillNewForm(Model model) {
		model.addAttribute("bill", new Bill());
		model.addAttribute("listPolicies", policyRepository.findAll());
		return "bill_form";
	}
	
	@PostMapping("/bills/save")
	public String saveBill(Bill bill, HttpServletRequest request) {
		int last = policyRepository.findAll().size();
		Policy p = policyRepository.findAll().get(last-1);
		bill.setPolicy(p);
		billRepository.save(bill);
		return "redirect:/bills";
	}
	
	@GetMapping("/bills/edit/{id}")
	public String showBillEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("bill", billRepository.findById(id).get());
		model.addAttribute("listPolicies", policyRepository.findAll());
		return "bill_form";
	}
	
	@GetMapping("/bills/delete/{id}")
	public String deleteBill(@PathVariable Integer id, Model model) {
		billRepository.deleteById(id);
		return "redirect:/bills";
	}
}
