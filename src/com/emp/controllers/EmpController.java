package com.emp.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emp.beans.Emp;
import com.emp.beans.LoginBean;
import com.emp.dao.EmpTablesDao;

import generateEmployeeTables.EmployeeTables;
@Controller    
public class EmpController {
	/* static Log log = LogFactory.getLog(EmpController.class.getName()); */
	/*
	 * static final Logger LOGGER =
	 * LogManager.getLogger(EmpController.class.getName());
	 */
	final static Logger LOGGER = LogManager.getLogger(EmpController.class);
    @Autowired    
    EmpTablesDao empDao;
    /*It displays a form to input data, here "command" is a reserved request attribute  
     *which is used to display object data into form  
     */    
    @RequestMapping("/empform")    
    public String showform(Model m){    
        m.addAttribute("command", new Emp());  
        return "empform";   
    }
    
    /*@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }*/	
    /*It saves object into database. The @ModelAttribute puts request data  
     *  into model object. You need to mention RequestMethod.POST method   
     *  because default request is GET*/    
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("emp") EmployeeTables emp){    
        empDao.save(emp);    
        return "redirect:/viewemp";//will redirect to viewemp request mapping    
    }    
    /* It provides list of employees in model object */    
    /*@RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<Emp> list=empDao.getEmployees();    
        m.addAttribute("list",list);  
        return "viewemp";    
    }*/
    
    
	/* uncomment if you need the implementation*/
	/*
	 * @RequestMapping("/viewemp") public String viewemp(Model m){
	 * List<EmployeeTables> list=empDao.getEmployees(); m.addAttribute("list",list);
	 * return "viewemp"; }
	 */  
    
	/* comment out if you dont need the default harcoded values */ 
    @RequestMapping(value="/viewemp")    
    public String viewemp(Model m){    
        EmployeeTables list=empDao.getEmployees();    
        m.addAttribute("usa",list.getDesignation());
        m.addAttribute("barry",list.getName());
        m.addAttribute("1",list.getId());
        m.addAttribute("1000",list.getSalary());
        return "viewemp";    
    }
    
    /* It displays object data into form for the given id.   
     * The @PathVariable puts URL data into variable.*/    
    @RequestMapping(value="/editemp/{id}")    
    public String edit(@PathVariable int id, Model m){    
    	EmployeeTables emp=empDao.getEmpById(id);    
        m.addAttribute("command",emp);  
        return "empeditform";    
    }    
    /* It updates model object. */    
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") EmployeeTables emp){    
        empDao.update(emp);    
        return "redirect:/viewemp";    
    }    
    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
        empDao.delete(id);    
        return "redirect:/viewemp";    
    } 
    
	/*
	 * @RequestMapping(value="/loginvalidation/{username}/{password}",method =
	 * RequestMethod.POST) public ModelAndView loginValidation(@PathVariable String
	 * username,String password){ ModelAndView modelAndView = new ModelAndView();
	 * if(empDao.getUserToValidate(username, password)!=0 ) {
	 * modelAndView.setViewName("redirect:/viewemp"); return modelAndView; }else {
	 * modelAndView.setViewName("logout"); return modelAndView; }
	 * 
	 * }
	 */
    @RequestMapping(value="/loginvalidation/{username}/{password}", method=RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int createLoginForm(@RequestBody EmployeeTables empTablesDao) {
        return empDao.save(empTablesDao);
    }
    
    @RequestMapping(value ={"/", "/login"},method = RequestMethod.GET)
    public String init(Model model) throws InterruptedException {
    	//log it via log4j
    	  if(LOGGER.isDebugEnabled()){
    	   LOGGER.debug("Start debug");
    	  }
    	  LOGGER.info("Going to run HelloLoggingController class");
    	  LOGGER.debug("Going to run HelloLoggingController class");
		/* for(int i = 0; i < 2000; i++) { */
    	  LOGGER.fatal("Going to run HelloLoggingController class");
		/*
		 * Thread.sleep(100); }
		 */
    	  LOGGER.warn("Going to run HelloLoggingController class");
      model.addAttribute("msg", "Please Enter Your Login Details");
      LOGGER.info("Exiting the program");
	  LOGGER.info("Going to run HelloLoggingController class");
	  LOGGER.debug("Going to run HelloLoggingController class");
	  LOGGER.fatal("Going to run HelloLoggingController class");
	  LOGGER.warn("Going to run HelloLoggingController class");
      return "login";
    }
  
    // Specify name of a specific view that will be used to display the error:
	/*
	 * @ExceptionHandler({SQLException.class,DataAccessException.class}) public
	 * String databaseError() { // Nothing to do. Returns the logical view name of
	 * an error page, passed // to the view-resolver(s) in usual way. // Note that
	 * the exception is NOT available to this view (it is not added // to the model)
	 * but see "Extending ExceptionHandlerExceptionResolver" // below. //log it via
	 * log4j
	 * 
	 * if(LOGGER.isDebugEnabled()){ LOGGER.debug("Start debug"); }
	 * LOGGER.info("Going to run HelloLoggingController class");
	 * LOGGER.info("Exiting the program"); return "login"; }
	 */
    @RequestMapping(method = RequestMethod.POST)
    public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean,@ModelAttribute("userName") String userName,@ModelAttribute("password") String password,HttpServletRequest httpRequest) {
      if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
    	  if (empDao.getEmpByName(userName, password) != null) {
          return "redirect:/viewemp";
        }
    }
      if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Start debug");
    	  }
  	LOGGER.info("Going to run HelloLoggingController class");
  	LOGGER.info("Exiting the program");
      return "login";
      
    }
}