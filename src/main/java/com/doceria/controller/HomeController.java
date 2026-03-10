package com.doceria.controller;

import com.doceria.model.Produto;
import com.doceria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import com.doceria.model.ItemVenda;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @ModelAttribute
    public void addCarrinhoInfo(Model model, HttpSession session) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        int count = 0;
        if (carrinho != null) {
            for (ItemVenda item : carrinho) {
                count += item.getQuantidade();
            }
        }
        model.addAttribute("cartCount", count);
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        model.addAttribute("doces", produtos.stream().filter(p -> "doce".equals(p.getCategoria())).collect(Collectors.toList()));
        model.addAttribute("bolos", produtos.stream().filter(p -> "bolo".equals(p.getCategoria())).collect(Collectors.toList()));

        // Verificar se há mensagem de sucesso na sessão
        String mensagemSucesso = (String) session.getAttribute("mensagemSucesso");
        if (mensagemSucesso != null) {
            model.addAttribute("mensagemSucesso", mensagemSucesso);
            session.removeAttribute("mensagemSucesso"); // Remover da sessão após exibir
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}