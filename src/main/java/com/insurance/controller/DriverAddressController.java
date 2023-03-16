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
import com.insurance.model.Driver;
import com.insurance.model.DriverAddress;
import com.insurance.repository.DriverAddressRepository;
import com.insurance.repository.DriverRepository;

@Controller
public class DriverAddressController {

	@Autowired
	private DriverAddressRepository driverAddressRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@GetMapping("/driverAddresss")
	public String listDriverAddresss(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/driverAddresss/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<DriverAddress> page = driverAddressRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listDriverAddresss", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "driverAddresss";
	}
	
	@GetMapping("/driverAddresss/new")
	public String showDriverAddressNewForm(Model model) {
		model.addAttribute("driverAddress", new DriverAddress());
		model.addAttribute("listDrivers", driverRepository.findAll());
		return "driverAddress_form";
	}
	
	@PostMapping("/driverAddresss/save")
	public String saveDriverAddress(DriverAddress driverAddress, HttpServletRequest request) {
		int last = driverRepository.findAll().size();
		Driver p = driverRepository.findAll().get(last-1);
		driverAddress.setDriver(p);
		driverAddressRepository.save(driverAddress);
		return "redirect:/driverAddresss";
	}
	
	@GetMapping("/driverAddresss/edit/{id}")
	public String showDriverAddressEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("driverAddress", driverAddressRepository.findById(id).get());
		model.addAttribute("listDrivers", driverRepository.findAll());
		return "driverAddress_form";
	}
	
	@GetMapping("/driverAddresss/delete/{id}")
	public String deleteDriverAddress(@PathVariable Integer id, Model model) {
		driverAddressRepository.deleteById(id);
		return "redirect:/driverAddresss";
	}
}
