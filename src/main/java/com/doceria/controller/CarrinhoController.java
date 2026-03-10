package com.doceria.controller;

import com.doceria.model.ItemVenda;
import com.doceria.model.Produto;
import com.doceria.service.ProdutoService;
import com.doceria.service.VendaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private VendaService vendaService;

    @SuppressWarnings("unchecked")
    @GetMapping
    public String verCarrinho(HttpSession session, Model model) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        model.addAttribute("carrinho", carrinho);

        // calcular total
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : carrinho) {
            total = total.add(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }
        model.addAttribute("total", total);
        return "carrinho";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/remover")
    public String removerDoCarrinho(@RequestParam Long produtoId, HttpSession session) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho != null) {
            carrinho.removeIf(item -> item.getProduto().getId().equals(produtoId));
            session.setAttribute("carrinho", carrinho);
        }
        return "redirect:/carrinho";
    }

    @PostMapping("/adicionar")
    public String adicionarAoCarrinho(@RequestParam Long produtoId, @RequestParam int quantidade, HttpSession session,
                                      @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        Produto produto = produtoService.buscarPorId(produtoId).orElseThrow();
        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(item);
        session.setAttribute("carrinho", carrinho);

        if ("XMLHttpRequest".equals(requestedWith)) {
            return ""; // return empty for AJAX call
        }
        return "redirect:/carrinho";
    }

    @GetMapping("/finalizar")
    public String finalizarCompra(HttpSession session, Model model) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho == null || carrinho.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("carrinho", carrinho);

        // calcular total
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : carrinho) {
            total = total.add(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/comprar")
    public String comprar(@RequestParam String metodoPagamento,
                         @RequestParam(required = false) String numeroCartao,
                         @RequestParam(required = false) String nomeCartao,
                         @RequestParam(required = false) String validadeMes,
                         @RequestParam(required = false) String validadeAno,
                         @RequestParam(required = false) String cvv,
                         @RequestParam(required = false) String cpf,
                         @RequestParam(required = false) String parcelas,
                         HttpSession session, Model model) {
        System.out.println("=== INICIANDO PROCESSAMENTO DE COMPRA ===");
        System.out.println("Método de pagamento: " + metodoPagamento);

        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho != null && !carrinho.isEmpty()) {
            // Aqui você implementaria a integração com gateway de pagamento
            if ("cartao".equals(metodoPagamento)) {
                System.out.println("Processando pagamento com cartão:");
                System.out.println("Número: " + numeroCartao);
                System.out.println("Nome: " + nomeCartao);
                System.out.println("Validade: " + validadeMes + "/" + validadeAno);
                System.out.println("CVV: " + cvv);
                System.out.println("CPF: " + cpf);
                System.out.println("Parcelas: " + parcelas);

                // Validações básicas
                if (numeroCartao == null || numeroCartao.trim().isEmpty()) {
                    model.addAttribute("erro", "Número do cartão é obrigatório.");
                    return "checkout";
                }
                if (nomeCartao == null || nomeCartao.trim().isEmpty()) {
                    model.addAttribute("erro", "Nome no cartão é obrigatório.");
                    return "checkout";
                }
                if (validadeMes == null || validadeAno == null) {
                    model.addAttribute("erro", "Validade do cartão é obrigatória.");
                    return "checkout";
                }
                if (cvv == null || cvv.trim().isEmpty()) {
                    model.addAttribute("erro", "CVV é obrigatório.");
                    return "checkout";
                }
                if (cpf == null || cpf.trim().isEmpty()) {
                    model.addAttribute("erro", "CPF é obrigatório.");
                    return "checkout";
                }

                // Em produção, aqui você chamaria o gateway de pagamento
                // Exemplo: boolean pagamentoAprovado = gateway.processarPagamento(dadosCartao, valor);

                // Para demonstração, sempre aprova
                boolean pagamentoAprovado = true;
                System.out.println("Pagamento aprovado: " + pagamentoAprovado);

                if (pagamentoAprovado) {
                    vendaService.salvarVenda(carrinho, metodoPagamento);
                    session.removeAttribute("carrinho");
                    // Adicionar mensagem de sucesso na sessão
                    session.setAttribute("mensagemSucesso", "Pagamento com cartão aprovado! Pedido realizado com sucesso.");
                    System.out.println("=== COMPRA FINALIZADA COM SUCESSO ===");
                    return "redirect:/";
                } else {
                    model.addAttribute("erro", "Pagamento rejeitado. Verifique os dados do cartão.");
                    return "checkout";
                }
            } else {
                // PIX ou outros métodos
                vendaService.salvarVenda(carrinho, metodoPagamento);
                session.removeAttribute("carrinho");
            }
        }
        return "redirect:/";
    }
}