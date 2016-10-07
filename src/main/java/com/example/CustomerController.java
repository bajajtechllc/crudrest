package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/

    @Autowired
    CustomerRepository repository;

 /*   @RequestMapping("/customer")
    public Customer customer() {
        Customer customer = repository.findOne(1L);
			log.info("*************************Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");
          return customer;
    }*/
    @RequestMapping("/customers")
    public List<Customer> customers() {
        List<Customer> customer = (List<Customer>)repository.findAll();
			log.info("*************************Customer found with findAll():");
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");
          return customer;
      }
    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public Customer customer(@RequestParam(value="firstName", defaultValue="firstName") String firstName,
    	@RequestParam(value="lastName", defaultValue="lastName") String lastName) {
        Customer customer = repository.save(new Customer(firstName, lastName));;
			
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");
          return customer;
    }
    @RequestMapping(value="/customer", method=RequestMethod.DELETE)
    public void customer(@RequestParam(value="id", defaultValue="1") Long id) {
        repository.delete(id);
		//return customer;
    }
    @RequestMapping(value="/customer", method=RequestMethod.PUT)
    public Customer customer(@RequestParam(value="id", defaultValue="1") Long id,@RequestParam(value="firstName", defaultValue="firstName") String firstName,
    	@RequestParam(value="lastName", defaultValue="lastName") String lastName) {
        Customer customer = repository.findOne(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return repository.save(customer);
		//return customer;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer customer(
	  @PathVariable("id") long id) {
	  	Customer customer = repository.findOne(id);
	    return customer;
	}
}