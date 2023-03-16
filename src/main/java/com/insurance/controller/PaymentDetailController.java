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
import com.insurance.model.Bill;
import com.insurance.model.PaymentDetail;
import com.insurance.repository.PaymentDetailRepository;
import com.insurance.repository.BillRepository;

@Controller
public class PaymentDetailController {

	@Autowired
	private PaymentDetailRepository paymentDetailRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@GetMapping("/paymentDetails")
	public String listPaymentDetails(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}
	
	@GetMapping("/paymentDetails/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<PaymentDetail> page = paymentDetailRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		System.out.println("Saluuuuuuuuuuuuuuuuute"+page.getContent().toString());
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listPaymentDetails", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "paymentDetails";
	}
	
	@GetMapping("/paymentDetails/new")
	public String showPaymentDetailNewForm(Model model) {
		model.addAttribute("paymentDetail", new PaymentDetail());
		model.addAttribute("listBills", billRepository.findAll());
		return "paymentDetail_form";
	}
	
	@PostMapping("/paymentDetails/save")
	public String savePaymentDetail(PaymentDetail paymentDetail, HttpServletRequest request) {
		int last = billRepository.findAll().size();
		Bill p = billRepository.findAll().get(last-1);
		paymentDetail.setBill(p);
		paymentDetailRepository.save(paymentDetail);
		return "redirect:/paymentDetails";
	}
	
	@GetMapping("/paymentDetails/edit/{id}")
	public String showPaymentDetailEditForm(@PathVariable Integer id, Model model) {
		model.addAttribute("paymentDetail", paymentDetailRepository.findById(id).get());
		model.addAttribute("listBills", billRepository.findAll());
		return "paymentDetail_form";
	}
	
	@GetMapping("/paymentDetails/delete/{id}")
	public String deletePaymentDetail(@PathVariable Integer id, Model model) {
		paymentDetailRepository.deleteById(id);
		return "redirect:/paymentDetails";
	}
}
