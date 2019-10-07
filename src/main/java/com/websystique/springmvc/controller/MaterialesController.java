package com.websystique.springmvc.controller;

import com.websystique.springmvc.dao.MaterialesDAO;
import com.websystique.springmvc.dao.OrdenesDAO;
import com.websystique.springmvc.model.Materiales;
import com.websystique.springmvc.model.Ordenes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class MaterialesController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
        
        
        
        
        
          /**
	 * METHODS FOR MATERIALES
	 */
             
    @RequestMapping(value = "/equipos/addmaterial",method=RequestMethod.GET)
    public ModelAndView addMaterial(HttpServletRequest request)
    {
        
        float p = Float.parseFloat(request.getParameter("precio"));
        int c = Integer.parseInt(request.getParameter("cantidad"));
        
        float total =  (float) (p*c);
        
        Materiales m = new Materiales();
        m.setMaterial(request.getParameter("material"));
        m.setPrecio(Float.parseFloat(request.getParameter("precio")));
        m.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        m.setCondicion(request.getParameter("condicion"));
        m.setOrdenFk(Integer.parseInt(request.getParameter("id")));
        m.setTotal(total);

           MaterialesDAO.insertMaterial(m);
                     
            return new ModelAndView("redirect:/equipos/completeorden?id="+request.getParameter("id"));
        
    
    }
    
    
    @RequestMapping(value ="/equipos/completeorden",method=RequestMethod.POST)
    public ModelAndView completarOrden(@ModelAttribute("orden") Ordenes p,HttpServletRequest request)
    {
        

            OrdenesDAO.updateOrden(p);
                     
             return new ModelAndView("redirect:/equipos/verequipo?id="+p.getEquipoFk());
        
    
    }
    
    
    @RequestMapping("/equipos/deletematerial")
    public ModelAndView deleteMaterial(HttpServletRequest request)
    {
        Materiales p = null;
        
        int id =Integer.parseInt(request.getParameter("id"));
        String ficha = request.getParameter("id");
        
        p = MaterialesDAO.selectMaterial(id);
        
        MaterialesDAO.deleteMaterial(p);
        
        return new ModelAndView("redirect:/equipos/verorden?id="+p.getOrdenFk()+"&ficha="+ficha);
       
    }  
    
        @RequestMapping("/equipos/deletematerialc")
    public ModelAndView deleteMaterialc(HttpServletRequest request)
    {
        Materiales p = null;
        
        int id =Integer.parseInt(request.getParameter("id"));

        p = MaterialesDAO.selectMaterial(id);
        
        MaterialesDAO.deleteMaterial(p);
        
        return new ModelAndView("redirect:/equipos/completeorden?id="+p.getOrdenFk());
       
    }  
    
    
	
	
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
        
        
        
        
    
    
    
  
    
      /**
	 * MANTENIMIENTOS LISTA
	 */
                                 
                            
    @ModelAttribute("mantenimientosLista")
    public Map<String,String> listadoMantenimientos()
    {
        Map<String,String> lst = new LinkedHashMap<>();
        lst.put("MANTENIMIENTO CORRECTIVO","MANTENIMIENTO CORRECTIVO");
        lst.put("MANTENIMIENTO PREVENTIVO","MANTENIMIENTO PREVENTIVO");

        return lst;
    }
    
    @ModelAttribute("estatusLista")
    public Map<String,String> listadoEstatus()
    {
        Map<String,String> lst = new LinkedHashMap<>();
        lst.put("COMPLETADA","COMPLETADA");

        return lst;
    }
    
    
    


}