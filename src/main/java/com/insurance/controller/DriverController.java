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
import com.insurance.model.Driver;
import com.insurance.repository.TrafficViolationCodeRepository;
import com.insurance.repository.DriverRepository;
import com.insurance.repository.PolicyRepository;
import com.insurance.repository.VehicleRepository;


@Controller
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private TrafficViolationCodeRepository trafficViolationCodeRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	
	@GetMapping("/drivers")
	public String listDrivers(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/drivers/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Driver> page = driverRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listDrivers", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "drivers";
	}
	
	@GetMapping("/drivers/new")
	public String showDriverNewForm(Model model) {
		model.addAttribute("listTrafficViolationCodes", trafficViolationCodeRepository.findAll());
		model.addAttribute("listPolicies", policyRepository.findAll());
		model.addAttribute("driver", new Driver());		
		model.addAttribute("listVehicles", vehicleRepository.findAll());

		return "driver_form";
	}
	
	@PostMapping("/drivers/save")
	public String saveDriver(Driver driver, HttpServletRequest request) {
		int last = policyRepository.findAll().size();
		Policy p = policyRepository.findAll().get(last-1);
		driver.setPolicy(p);
		driverRepository.save(driver);
		return "redirect:/drivers";
	}
	
	@GetMapping("/drivers/edit/{id}")
	public String showDriverEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listTrafficViolationCodes", trafficViolationCodeRepository.findAll());
		model.addAttribute("driver", driverRepository.findById(id).get());
		model.addAttribute("listPolicies", policyRepository.findAll());
		model.addAttribute("listVehicles", vehicleRepository.findAll());
		return "driver_form";
	}
	
	@GetMapping("/drivers/delete/{id}")
	public String deleteDriver(@PathVariable Integer id, Model model) {
		driverRepository.deleteById(id);
		return "redirect:/drivers";
	}
}
