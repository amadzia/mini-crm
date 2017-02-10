package pl.example.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.example.springdemo.entity.Customer;
import pl.example.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		//get list of customers from the service
		List<Customer> theCustomers=customerService.getCustomers();
		
		//add it to the model
		theModel.addAttribute("customers", theCustomers);
				
		return "list-customers";
		}
	
	@GetMapping("/formForAdd")
	public String formForAdd(Model theModel){
		
		//create model attribute
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		
		//save the customer
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/formForEdit")
	public String formForEdit(@RequestParam("customerId") int theId, Model theModel){
		
		//get the customer from service
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set customer as a model attribute to pre-populate form
		theModel.addAttribute("customer", theCustomer);
		
		//send data to the form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";		
	}
}

			
	