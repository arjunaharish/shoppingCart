package com.emp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.beans.Emp;
import com.emp.beans.LoginBean;
import com.emp.dao.EmpTablesDao;

import generateEmployeeTables.EmployeeTables;    
@Controller    
public class EmpController {    
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
    @RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<EmployeeTables> list=empDao.getEmployees();    
        m.addAttribute("list",list);  
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
    
    @RequestMapping(value="/loginvalidation/{username}/{password}",method = RequestMethod.POST)    
    public ModelAndView loginValidation(@PathVariable String username,String password){    
    	ModelAndView modelAndView = new ModelAndView();
    	if(empDao.getUserToValidate(username, password)!=0  ) {
    		modelAndView.setViewName("redirect:/viewemp");
            return modelAndView; 	
    	}else {
    		modelAndView.setViewName("logout");
            return modelAndView;	
    	}
    	 
    }  

    @RequestMapping(value="/loginvalidation/{username}/{password}", method=RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int createLoginForm(@RequestBody EmployeeTables empTablesDao) {
        return empDao.save(empTablesDao);
    }
    
    @RequestMapping(value ={"/", "/login"},method = RequestMethod.GET)
    public String init(Model model) {
      model.addAttribute("msg", "Please Enter Your Login Details");
      return "login";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) {
      if (loginBean != null && loginBean.getUserName() != null & loginBean.getPassword() != null) {
        if (loginBean.getUserName().equals("chandra") && loginBean.getPassword().equals("chandra123")) {
          model.addAttribute("msg", loginBean.getUserName());
          return "viewemp";
        } else {
          model.addAttribute("error", "Invalid Details");
          return "login";
        }
      } else {
        model.addAttribute("error", "Please enter Details");
        return "login";
      }
    }
}