package com.websystique.springmvc.controller;

import com.websystique.springmvc.dao.MaterialesDAO;
import com.websystique.springmvc.dao.OrdenesDAO;
import com.websystique.springmvc.model.Materiales;
import com.websystique.springmvc.model.Ordenes;
import java.util.List; 

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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OrdenesController {

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
	 * Methods for ORDENES
	 */
        
    @RequestMapping(value = "/equipos/ordenesview" ,method=RequestMethod.GET)
    public ModelAndView showOrdenes(HttpServletRequest request) throws Exception {
       ModelAndView mv = new ModelAndView("/equipos/ordenesview");
       
       try{
           PagedListHolder pagedListHolder;
           String busqueda =request.getParameter("busqueda");
           
           
           if(busqueda == null){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenes());
           }else if(busqueda!=null && busqueda.isEmpty()){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenes());
           }else if(busqueda.equals("*")){
                  pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenes());
           }else{
               pagedListHolder = new PagedListHolder(OrdenesDAO.buscarOrden(busqueda));
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
    
    
      @RequestMapping(value = "/equipos/ordenesopen" ,method=RequestMethod.GET)
    public ModelAndView showOrdenesOpen(HttpServletRequest request) throws Exception {
       ModelAndView mv = new ModelAndView("/equipos/ordenesopen");
       
       try{
           PagedListHolder pagedListHolder;
           String busqueda =request.getParameter("busqueda");
           
           
           if(busqueda == null){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesOpen());
           }else if(busqueda!=null && busqueda.isEmpty()){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesOpen());
           }else if(busqueda.equals("*")){
                  pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesOpen());
           }else{
               pagedListHolder = new PagedListHolder(OrdenesDAO.buscarOrdenOpen(busqueda));
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
    
    
        @RequestMapping(value = "/equipos/ordenescompletas" ,method=RequestMethod.GET)
    public ModelAndView showOrdenesComplete(HttpServletRequest request) throws Exception {
       ModelAndView mv = new ModelAndView("/equipos/ordenescompletas");
       
       try{
           PagedListHolder pagedListHolder;
           String busqueda =request.getParameter("busqueda");
           
           
           if(busqueda == null){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesCompletas());
           }else if(busqueda!=null && busqueda.isEmpty()){
               pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesCompletas());
           }else if(busqueda.equals("*")){
                  pagedListHolder = new PagedListHolder(OrdenesDAO.listOrdenesCompletas());
           }else{
               pagedListHolder = new PagedListHolder(OrdenesDAO.buscarOrdenCompletas(busqueda));
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
    
    
    
    @RequestMapping(value = "/equipos/neworden",method=RequestMethod.GET)
    public ModelAndView addOrdenView(HttpServletRequest request)
    {
        int id =Integer.parseInt(request.getParameter("id"));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/equipos/neworden");
        mav.addObject("orden", new Ordenes());
        mav.addObject("fk",id);
        return mav;
       
    }
    
    @RequestMapping(value = "/equipos/neworden",method=RequestMethod.POST)
    public ModelAndView addOrden(@ModelAttribute("orden") Ordenes p)
    {

           OrdenesDAO.insertOrden(p);
                     
            return new ModelAndView("redirect:/equipos/verequipo?id="+p.getEquipoFk());
        
    
    }
    
    
     @RequestMapping(value ="/equipos/editorden",method=RequestMethod.GET)
    public ModelAndView editOrdenView(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        int id =Integer.parseInt(request.getParameter("id"));
        Ordenes datos = OrdenesDAO.selectOrden(id);
        mav.setViewName("/equipos/editorden");
        mav.addObject("orden", new Ordenes(id,datos.getFechaCreacion(),datos.getFechaCierre(),datos.getTipoOrden(),datos.getDescripcion(),datos.getEquipoFk(),datos.getEstatus()));
        return mav;
       
    }                    


    @RequestMapping(value ="/equipos/editorden",method=RequestMethod.POST)
    public ModelAndView updateOrden(@ModelAttribute("orden") Ordenes p,HttpServletRequest request)
    {
        

            OrdenesDAO.updateOrden(p);
                     
             return new ModelAndView("redirect:/equipos/verequipo?id="+p.getEquipoFk());
        
    
    }
    
    
    
    @RequestMapping("/equipos/deleteorden")
    public ModelAndView deleteOrden(HttpServletRequest request)
    {
        Ordenes p = null;
        
        int id =Integer.parseInt(request.getParameter("id"));
        p = OrdenesDAO.selectOrden(id);
        
        OrdenesDAO.deleteOrden(p);
        
        return new ModelAndView("redirect:/equipos/verequipo?id="+p.getEquipoFk());
       
    }  
    
    
    @RequestMapping(value ="/equipos/verorden",method=RequestMethod.GET)
    public ModelAndView VerOrden(HttpServletRequest request)
    {
       
         ModelAndView mav = new ModelAndView();
         mav.setViewName("/equipos/verorden");
         PagedListHolder pagedListHolder;
         
         try{
            int id =Integer.parseInt(request.getParameter("id"));
            String ficha =request.getParameter("ficha");
            Ordenes datos = OrdenesDAO.selectOrden(id);
            pagedListHolder = new PagedListHolder(MaterialesDAO.selectMaterialesByOrdenFk(id));
            float total = 0;
            List<Materiales> lst = MaterialesDAO.selectMaterialesByOrdenFk(id);
            for (Iterator<Materiales> i = lst.iterator(); i.hasNext();) {
                Materiales item = i.next();
                total = total + item.getTotal();
            }

           int page = ServletRequestUtils.getIntParameter(request, "p", 0);
           pagedListHolder.setPage(page);
           pagedListHolder.setPageSize(10);
           mav.addObject("pagedListHolder",pagedListHolder);
           mav.addObject("orden", new Ordenes(id,datos.getFechaCreacion(),datos.getFechaCierre(),datos.getTipoOrden(),datos.getDescripcion(),datos.getEquipoFk(),datos.getEstatus()));
           mav.addObject("ficha",ficha);
           mav.addObject("total",total);
        
       }catch(Exception e){
           e.printStackTrace();
       }
        
        
        return mav;
       
    }    
    
    
    
      @RequestMapping(value ="/equipos/completeorden",method=RequestMethod.GET)
    public ModelAndView completeOrdenView(HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView();
        int id =Integer.parseInt(request.getParameter("id"));
        Ordenes dato = OrdenesDAO.selectOrden(id);
        PagedListHolder pagedListHolder;
        mav.setViewName("/equipos/completeorden");
        mav.addObject("orden", new Ordenes(id,dato.getFechaCreacion(),dato.getFechaCierre(),dato.getTipoOrden(),dato.getDescripcion(),dato.getEquipoFk(),dato.getEstatus()));
        
        try{
            
            pagedListHolder = new PagedListHolder(MaterialesDAO.selectMaterialesByOrdenFk(id));
        

           int page = ServletRequestUtils.getIntParameter(request, "p", 0);
           pagedListHolder.setPage(page);
           pagedListHolder.setPageSize(10);
           mav.addObject("pagedListHolder",pagedListHolder);
           
            
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