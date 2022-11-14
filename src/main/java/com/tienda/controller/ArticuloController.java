
package com.tienda.controller;


import com.tienda.domain.Articulo;
import com.tienda.service.ArticuloService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticuloController {
    
    @Autowired
    private ArticuloService articuloService;
    
    
    @GetMapping("/articulo/listado")
    public String listado(Model model){
        
        var articulos=articuloService.getArticulos(false); 
        
        model.addAttribute("articulos",articulos);
        return "/articulo/listado";
    }
    
    @GetMapping("/nuevoArticulo")
    public String nuevoArticulo(Articulo articulo){
        return "/articulo/modificar";
    }
    @PostMapping("/guardarArticulo")
    public String guardarArticulo(Articulo articulo){
        articuloService.save(articulo);
        return "redirect:/articulo/listado";
    }
    
    @GetMapping("/modificarArticulo/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model){
        articulo= articuloService.getArticulo(articulo);
        model.addAttribute("articulo", articulo);
        return "/articulo/modificar";
    }
    
    @GetMapping("/eliminarArticulo/{idArticulo}")
    public String eliminarArticulo(Articulo articulo){
        articuloService.delete(articulo);
        return "redirect:/articulo/listado";
    }
    
}
