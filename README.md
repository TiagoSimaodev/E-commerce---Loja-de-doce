# 🍰 Doceria Delícia - E-commerce

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.0-purple)](https://getbootstrap.com/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

Uma aplicação web completa de e-commerce para uma doceria artesanal, desenvolvida com Spring Boot, Thymeleaf, Bootstrap e PostgreSQL. Oferece uma experiência de compra intuitiva com catálogo de produtos, carrinho de compras, sistema de pagamentos (PIX e cartão de crédito) e painel administrativo.

## 📋 Índice

- [🚀 Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias Utilizadas](#%EF%B8%8F-tecnologias-utilizadas)
- [📋 Pré-requisitos](#-pré-requisitos)
- [⚙️ Instalação e Configuração](#%EF%B8%8F-instalação-e-configuração)
- [🏃‍♂️ Como Executar](#%EF%B8%8F-como-executar)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [🎯 Como Usar](#-como-usar)
- [🔐 Sistema de Segurança](#-sistema-de-segurança)
- [💳 Sistema de Pagamentos](#-sistema-de-pagamentos)
- [🗄️ Banco de Dados](#%EF%B8%8F-banco-de-dados)
- [🧪 Testes](#-testes)
- [🚀 Deploy](#-deploy)
- [🤝 Contribuição](#-contribuição)
- [📝 Licença](#-licença)
- [👨‍💻 Autor](#%EF%B8%8F-autor)

## 🚀 Funcionalidades

### 🛒 Para Clientes
- ✅ **Catálogo de Produtos**: Visualização de doces e bolos artesanais
- ✅ **Carrinho de Compras**: Adição/removal de itens com AJAX
- ✅ **Sistema de Checkout**: Processo de finalização de compra intuitivo
- ✅ **Múltiplas Formas de Pagamento**: PIX com QR Code e cartão de crédito
- ✅ **Parcelamento**: Até 6x sem juros
- ✅ **Responsividade**: Interface otimizada para desktop e mobile
- ✅ **Feedback Visual**: Mensagens de sucesso e validações em tempo real

### 👨‍💼 Para Administradores
- ✅ **Painel Administrativo**: Interface dedicada para gestão
- ✅ **CRUD de Produtos**: Criar, editar, excluir produtos
- ✅ **Upload de Imagens**: Suporte a arquivos de imagem
- ✅ **Relatórios de Vendas**: Histórico completo de vendas
- ✅ **Autenticação Segura**: Login obrigatório para área administrativa

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação principal
- **Spring Boot 3.2.0** - Framework para desenvolvimento web
- **Spring MVC** - Padrão arquitetural Model-View-Controller
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **Maven** - Gerenciamento de dependências

### Frontend
- **Thymeleaf** - Template engine para Java
- **Bootstrap 5.3.0** - Framework CSS responsivo
- **JavaScript (ES6+)** - Interatividade e validações
- **QRCode.js** - Geração de QR Codes para PIX
- **Font Awesome** - Ícones vetoriais

### Banco de Dados
- **PostgreSQL 15** - Sistema de gerenciamento de banco de dados relacional
- **HikariCP** - Pool de conexões JDBC

### Desenvolvimento
- **Git** - Controle de versão
- **GitHub** - Hospedagem do código fonte

## 📋 Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+** para gerenciamento de dependências
- **PostgreSQL 12+** para o banco de dados
- **Git** para controle de versão

## ⚙️ Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone https://github.com/TiagoSimaodev/E-commerce---Loja-de-doce.git
cd E-commerce---Loja-de-doce
```

### 2. Configure o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE doceria_db;
```

### 3. Configure as Propriedades da Aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/doceria_db
spring.datasource.username=seu_usuario_postgresql
spring.datasource.password=sua_senha_postgresql
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configurações de Upload
spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=20MB

# Configurações do Servidor
server.port=8080
```

### 4. Instale as Dependências

```bash
mvn clean install
```

## 🏃‍♂️ Como Executar

### Desenvolvimento

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

### Produção

```bash
mvn clean package
java -jar target/doceria-app-0.0.1-SNAPSHOT.jar
```

## 📁 Estrutura do Projeto

```
doceria-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/doceria/
│   │   │       ├── config/           # Configurações (Security, etc.)
│   │   │       ├── controller/       # Controladores web
│   │   │       ├── model/           # Entidades JPA
│   │   │       ├── repository/      # Repositórios de dados
│   │   │       └── service/         # Lógica de negócio
│   │   └── resources/
│   │       ├── static/              # CSS, JS, imagens
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── uploads/         # Imagens dos produtos
│   │       ├── templates/           # Templates Thymeleaf
│   │       │   ├── admin/           # Templates administrativos
│   │       │   ├── carrinho.html
│   │       │   ├── checkout.html
│   │       │   ├── index.html
│   │       │   └── login.html
│   │       └── application.properties
│   └── test/                        # Testes unitários
├── target/                          # Arquivos compilados
├── pom.xml                          # Dependências Maven
└── README.md                        # Documentação
```

## 🎯 Como Usar

### Para Clientes

1. **Navegação**: Acesse a página inicial para ver o catálogo de produtos
2. **Adicionar ao Carrinho**: Clique em "Adicionar" nos produtos desejados
3. **Ver Carrinho**: Clique no ícone do carrinho no menu superior
4. **Finalizar Compra**: Clique em "Finalizar Pedido"
5. **Pagamento**: Escolha entre PIX ou cartão de crédito
6. **Confirmação**: Receba confirmação da compra

### Para Administradores

1. **Login**: Acesse `/login` com usuário `admin` e senha `admin`
2. **Gerenciar Produtos**: Use o menu administrativo para CRUD de produtos
3. **Upload de Imagens**: Adicione imagens aos produtos
4. **Ver Vendas**: Acesse relatórios de vendas em `/vendas`

## 🔐 Sistema de Segurança

- **Spring Security** para autenticação e autorização
- **CSRF Protection** em todos os formulários
- **Acesso público** ao catálogo e carrinho
- **Acesso restrito** à administração (login obrigatório)
- **Senhas criptografadas** (BCrypt)

## 💳 Sistema de Pagamentos

### PIX
- ✅ **QR Code gerado dinamicamente**
- ✅ **Código copia e cola**
- ✅ **Configuração personalizável** (chave PIX, nome, cidade)

### Cartão de Crédito
- ✅ **Formulário validado**
- ✅ **Máscara automática** no número do cartão
- ✅ **Formatação de CPF**
- ✅ **Validação de data de expiração**
- ✅ **Parcelamento** até 6x
- ✅ **Processamento simulado** (pronto para integração com gateways)

## 🗄️ Banco de Dados

### Entidades Principais

#### Produto
```sql
- id: BIGINT (PK)
- nome: VARCHAR(255)
- descricao: VARCHAR(1000)
- preco: DECIMAL(10,2)
- categoria: VARCHAR(50)
- imagemUrl: VARCHAR(500)
```

#### Venda
```sql
- id: BIGINT (PK)
- data: TIMESTAMP
- metodoPagamento: VARCHAR(50)
- total: DECIMAL(10,2)
```

#### ItemVenda
```sql
- id: BIGINT (PK)
- venda_id: BIGINT (FK)
- produto_id: BIGINT (FK)
- quantidade: INTEGER
- precoUnitario: DECIMAL(10,2)
```

## 🧪 Testes

### Executar Testes

```bash
mvn test
```

### Cobertura de Testes

```bash
mvn test jacoco:report
```

## 🚀 Deploy

### Docker (Recomendado)

```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# Build e execução
docker build -t doceria-app .
docker run -p 8080:8080 doceria-app
```

### Heroku

```bash
# Configurar Procfile
echo "web: java -jar target/*.jar --server.port=$PORT" > Procfile

# Deploy
git push heroku main
```

### AWS/Azure/GCP

Configure as variáveis de ambiente e faça deploy usando os serviços de container das plataformas.

## 🤝 Contribuição

Contribuições são bem-vindas! Siga estes passos:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Padrões de Código

- Use Java 17 features
- Siga os padrões do Spring Boot
- Mantenha cobertura de testes acima de 80%
- Use commits semânticos

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Tiago Silva**
- GitHub: [@TiagoSimaodev](https://github.com/TiagoSimaodev)
- LinkedIn: [Seu LinkedIn]
- Email: contato@doceria.com

---

⭐ **Dê uma estrela se este projeto te ajudou!**

Feito com ❤️ e muito ☕ para amantes de doces artesanais.
- `/vendas`: Ver vendas realizadas.

Para pagamentos, é simulado - apenas registra o método escolhido.