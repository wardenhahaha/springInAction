package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	public OrderRepository orderRepo;
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order,Errors errors,SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			log.info("Order submitted: " + order+errors.toString());
			return "orderForm";
		}
		log.info("Order submitted: " + order+errors.toString());
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	} 
}