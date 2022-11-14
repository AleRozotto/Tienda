
package com.tienda.controller;


import com.tienda.domain.Cliente;
import com.tienda.service.ArticuloService;
import com.tienda.service.ClienteService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class IndexController {
    
    @Autowired
    private ArticuloService articuloService;
    
    @GetMapping("/")
    public String inicio(Model model){
        var articulos=articuloService.getArticulos(true); 
        
        model.addAttribute("articulos",articulos);
        return "index";
    }
    
    //ClienteController
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/cliente/listado")
    public String listado(Model model){
        
        var clientes=clienteService.getClientes(); 
        
        model.addAttribute("clientes",clientes);
        return "/cliente/listado";
    }
    
    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Cliente cliente){
        return "/cliente/modificar";
    }
    @PostMapping("/guardarCliente")
    public String guardarCliente(Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/cliente/listado";
    }
    
    @GetMapping("/modificarCliente/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model){
        cliente= clienteService.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/cliente/modificar";
    }
    
    @GetMapping("/eliminarCliente/{idCliente}")
    public String eliminarCliente(Cliente cliente){
        clienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }
    
    @GetMapping("/cliente/busque")
    public String busqueda (Cliente cliente) {
        return "/cliente/busqueda";
    }
    
    @PostMapping("/cliente/encontrado")
    public String encontrado (Model model, String Apellidos) {
       var clientes=clienteService.buscarPorApellidos(Apellidos);
       model.addAttribute("clientes", clientes);
        return "redirect:/cliente/listado";
    }
    
    @GetMapping("/cliente/buscar")
    public String buscar( Model model) {
       var clientes=clienteService.buscarPorApellidos("Castro Mora");
       model.addAttribute("clientes", clientes);
        return "/cliente/listado";
    }
    
}
