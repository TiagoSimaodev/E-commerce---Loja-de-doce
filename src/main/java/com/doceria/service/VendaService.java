package com.doceria.service;

import com.doceria.model.ItemVenda;
import com.doceria.model.Produto;
import com.doceria.model.Venda;
import com.doceria.repository.ProdutoRepository;
import com.doceria.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda salvarVenda(List<ItemVenda> itens, String metodoPagamento) {
        Venda venda = new Venda();
        venda.setData(LocalDateTime.now());
        venda.setMetodoPagamento(metodoPagamento);
        venda.setItens(itens);

        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda item : itens) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();
            item.setPrecoUnitario(produto.getPreco());
            total = total.add(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }
        venda.setTotal(total);

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }
}