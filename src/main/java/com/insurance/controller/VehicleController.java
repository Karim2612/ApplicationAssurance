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
import com.insurance.model.Vehicle;
import com.insurance.repository.VehicleRepository;
import com.insurance.repository.PolicyRepository;
import com.insurance.repository.CoverageRepository;

@Controller
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@GetMapping("/vehicles")
	public String listVehicles(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/vehicles/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Vehicle> page = vehicleRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listVehicles", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "vehicles";
	}
	
	@GetMapping("/vehicles/new")
	public String showVehicleNewForm(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		model.addAttribute("listPolicies", policyRepository.findAll());
		model.addAttribute("listCoverages", coverageRepository.findAll());
		return "vehicle_form";
	}
	
	@PostMapping("/vehicles/save")
	public String saveVehicle(Vehicle vehicle, HttpServletRequest request) {
		int last = policyRepository.findAll().size();
		Policy p = policyRepository.findAll().get(last-1);
		vehicle.setPolicy(p);
		vehicleRepository.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@GetMapping("/vehicles/edit/{id}")
	public String showVehicleEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("vehicle", vehicleRepository.findById(id).get());
		model.addAttribute("listPolicies", policyRepository.findAll());
		model.addAttribute("listCoverages", coverageRepository.findAll());
		return "vehicle_form";
	}
	
	@GetMapping("/vehicles/delete/{id}")
	public String deleteVehicle(@PathVariable Integer id, Model model) {
		vehicleRepository.deleteById(id);
		return "redirect:/vehicles";
	}
}
