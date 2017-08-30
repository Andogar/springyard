package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public String customerList(Model model) {
        model.addAttribute("customers", customerService.get());
        return "customers";
    }

    @RequestMapping("/customers/{customerId}")
    public String singleCustomer(@PathVariable int customerId, Model model) {
        Customer singleCustomer = customerService.getById(customerId);
        model.addAttribute("customer", singleCustomer);
        return "single_customer";
    }

    @RequestMapping("/new_customer")
    public String newCustomer(Model model) {
        return "new_customer";
    }

    @RequestMapping("/add_customer")
    public String createCustomer(@RequestParam(value = "first_name") String firstName, @RequestParam(value = "last_name") String lastName, @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phone, Model model) {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);

        customerService.add(customer);

        return "redirect:/customers";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loggedout")
    String logout(Model model) {
        return "redirect:/customers";
    }

}
