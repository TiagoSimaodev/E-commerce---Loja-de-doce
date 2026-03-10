package com.doceria.config;

import com.doceria.model.Produto;
import com.doceria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (produtoRepository.count() == 0) {
            Produto brigadeiro = new Produto();
            brigadeiro.setNome("Brigadeiro");
            brigadeiro.setDescricao("Doce cremoso de chocolate");
            brigadeiro.setPreco(new BigDecimal("5.00"));
            brigadeiro.setCategoria("doce");
            brigadeiro.setImagemUrl("https://via.placeholder.com/300x200?text=Brigadeiro");
            produtoRepository.save(brigadeiro);

            Produto beijinho = new Produto();
            beijinho.setNome("Beijinho");
            beijinho.setDescricao("Doce de coco com leite condensado");
            beijinho.setPreco(new BigDecimal("4.50"));
            beijinho.setCategoria("doce");
            beijinho.setImagemUrl("https://via.placeholder.com/300x200?text=Beijinho");
            produtoRepository.save(beijinho);

            Produto trufa = new Produto();
            trufa.setNome("Trufa de Chocolate");
            trufa.setDescricao("Trufa recheada com chocolate");
            trufa.setPreco(new BigDecimal("7.00"));
            trufa.setCategoria("doce");
            trufa.setImagemUrl("https://via.placeholder.com/300x200?text=Trufa");
            produtoRepository.save(trufa);

            Produto boloChocolate = new Produto();
            boloChocolate.setNome("Bolo de Chocolate");
            boloChocolate.setDescricao("Bolo fofinho com cobertura de chocolate");
            boloChocolate.setPreco(new BigDecimal("25.00"));
            boloChocolate.setCategoria("bolo");
            boloChocolate.setImagemUrl("https://via.placeholder.com/300x200?text=Bolo+Chocolate");
            produtoRepository.save(boloChocolate);

            Produto boloCenoura = new Produto();
            boloCenoura.setNome("Bolo de Cenoura");
            boloCenoura.setDescricao("Bolo clássico com cobertura de chocolate");
            boloCenoura.setPreco(new BigDecimal("20.00"));
            boloCenoura.setCategoria("bolo");
            boloCenoura.setImagemUrl("https://via.placeholder.com/300x200?text=Bolo+Cenoura");
            produtoRepository.save(boloCenoura);

            Produto pudim = new Produto();
            pudim.setNome("Pudim");
            pudim.setDescricao("Pudim de leite condensado");
            pudim.setPreco(new BigDecimal("8.00"));
            pudim.setCategoria("doce");
            pudim.setImagemUrl("https://via.placeholder.com/300x200?text=Pudim");
            produtoRepository.save(pudim);
        }
    }
}