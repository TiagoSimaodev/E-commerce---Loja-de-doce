package com.doceria.controller;

import com.doceria.model.Produto;
import com.doceria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "admin/dashboard";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "admin/produto-form";
    }

    @PostMapping("/produtos")
    public String salvarProduto(@ModelAttribute Produto produto,
                                @RequestParam(value = "imagemFile", required = false) MultipartFile imagemFile) {
        // trata upload de imagem
        if (imagemFile != null && !imagemFile.isEmpty()) {
            try {
                String uploadsDir = "src/main/resources/static/uploads/";
                File dir = new File(uploadsDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String original = imagemFile.getOriginalFilename();
                String ext = "";
                if (original != null && original.contains(".")) {
                    ext = original.substring(original.lastIndexOf('.'));
                }
                String filename = UUID.randomUUID().toString() + ext;
                Path filePath = Paths.get(uploadsDir + filename);
                Files.write(filePath, imagemFile.getBytes());
                produto.setImagemUrl("/uploads/" + filename);
            } catch (IOException e) {
                // log falha no upload, continuar sem imagem
                e.printStackTrace();
            }
        }
        produtoService.salvar(produto);
        return "redirect:/admin";
    }

    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id).orElseThrow();
        model.addAttribute("produto", produto);
        return "admin/produto-form";
    }

    @GetMapping("/produtos/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/admin";
    }
}