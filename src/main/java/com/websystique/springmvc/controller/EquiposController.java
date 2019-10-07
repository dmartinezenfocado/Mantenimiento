package com.websystique.springmvc.controller;

import com.websystique.springmvc.dao.EquiposDAO;
import com.websystique.springmvc.dao.OrdenesDAO;
import com.websystique.springmvc.model.Equipos;

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
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class EquiposController {

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
	 * Methods for EQUIPOS
	 */
        
    @RequestMapping(value = "/equipos/equiposview" ,method=RequestMethod.GET)
    public ModelAndView showEquipos(HttpServletRequest request) throws Exception {
       ModelAndView mv = new ModelAndView("/equipos/equiposview");
       
       try{
           PagedListHolder pagedListHolder;
           String busqueda =request.getParameter("busqueda");
           
           
           if(busqueda == null){
               pagedListHolder = new PagedListHolder(EquiposDAO.listEquipos());
           }else if(busqueda!=null && busqueda.isEmpty()){
               pagedListHolder = new PagedListHolder(EquiposDAO.listEquipos());
           }else if(busqueda.equals("*")){
                  pagedListHolder = new PagedListHolder(EquiposDAO.listEquipos());
           }else{
               pagedListHolder = new PagedListHolder(EquiposDAO.buscarEquipo(busqueda));
           }
           
           int page = ServletRequestUtils.getIntParameter(request, "p", 0);
           pagedListHolder.setPage(page);
           pagedListHolder.setPageSize(10);
           mv.addObject("pagedListHolder",pagedListHolder);
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return mv;
    }
    
    
     
    @RequestMapping(value = "/equipos/addequipo",method=RequestMethod.GET)
    public ModelAndView addEquipoView()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/equipos/addequipo");
        mav.addObject("equipo", new Equipos());
        return mav;
       
    }
    
    
    @RequestMapping(value = "/equipos/addequipo",method=RequestMethod.POST)
    public ModelAndView addEquipo(@ModelAttribute("equipo") Equipos p)
    {

           EquiposDAO.insertEquipo(p);
                     
            return new ModelAndView("redirect:/equipos/equiposview");
        
    
    }
    
    
    
     @RequestMapping(value ="/equipos/editequipo",method=RequestMethod.GET)
    public ModelAndView editEquipoView(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        int id =Integer.parseInt(request.getParameter("id"));
        Equipos datos = EquiposDAO.selectEquipo(id);
        mav.setViewName("/equipos/editequipo");
        mav.addObject("equipo", new Equipos(id,datos.getTipo(),datos.getMarca(),datos.getModelo(),datos.getAno(),datos.getSerie(),datos.getFicha()));
        return mav;
       
    }                    


    @RequestMapping(value ="/equipos/editequipo",method=RequestMethod.POST)
    public ModelAndView updateEquipo(@ModelAttribute("equipo") Equipos p,HttpServletRequest request)
    {
        

            EquiposDAO.updateEquipo(p);
                     
             return new ModelAndView("redirect:/equipos/equiposview");
        
    
    }
    
    @RequestMapping("/equipos/deleteequipo")
    public ModelAndView deleteEquipo(HttpServletRequest request)
    {
        Equipos p = null;
        
        int id =Integer.parseInt(request.getParameter("id"));
        p = EquiposDAO.selectEquipo(id);
        
        EquiposDAO.deleteEquipo(p);
        
        return new ModelAndView("redirect:/equipos/equiposview");
       
    }  
    
    
    @RequestMapping(value ="/equipos/verequipo",method=RequestMethod.GET)
    public ModelAndView VerEquipo(HttpServletRequest request)
    {
       
         ModelAndView mav = new ModelAndView();
         mav.setViewName("/equipos/verequipo");
         PagedListHolder pagedListHolder;
         
         try{
            int id =Integer.parseInt(request.getParameter("id"));
            Equipos datos = EquiposDAO.selectEquipo(id);
            pagedListHolder = new PagedListHolder(OrdenesDAO.selectOrdenesByEquipoFk(id));
        

           int page = ServletRequestUtils.getIntParameter(request, "p", 0);
           pagedListHolder.setPage(page);
           pagedListHolder.setPageSize(10);
           mav.addObject("pagedListHolder",pagedListHolder);
           mav.addObject("equipo", new Equipos(id,datos.getTipo(),datos.getMarca(),datos.getModelo(),datos.getAno(),datos.getSerie(),datos.getFicha()));

        
       }catch(Exception e){
           e.printStackTrace();
       }
        
        
        return mav;
       
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