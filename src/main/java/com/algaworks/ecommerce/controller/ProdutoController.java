package com.algaworks.ecommerce.controller;

import com.algaworks.ecommerce.repository.Produtos;
import com.algaworks.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private Produtos produtos;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("produtos/produtos-lista");
        mv.addObject("produtos", produtos.listar());
        return mv;
    }
}
