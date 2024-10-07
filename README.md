# Escopo
# Contexto

Uma empresa de marketing deseja uma aplicação para analisar as preferências de cada usuário e direcionar a eles apenas anúncios de seu interesse. A aplicação deve ser desenvolvida tendo em mente que futuramente ela poderá ser implementada em plataformas e aplicações de terceiros.

# Ferramentas

- Linguagem de desenvolvimento: Java
- Manipulação e persistência de dados: Serialização local (data.txt)
- Interface do usuário: Java Swing

# Escopo

## Objetivos:

- **Personalização de Anúncios:** Desenvolver um sistema que analisa as preferências de cada usuário para oferecer anúncios relevantes.
- **Melhorar a Experiência do Usuário:** Criar uma interface amigável que permita aos usuários navegar facilmente e interagir com os anúncios.
- **Coleta de Dados:** Implementar um sistema para coletar e armazenar dados sobre as interações dos usuários com os anúncios, ajudando na análise de desempenho.
- **Relatórios de Desempenho:** Fornecer relatórios detalhados sobre a eficácia dos anúncios.
- **Escalabilidade:** Garantir que a aplicação possa ser facilmente expandida para incluir novas funcionalidades e integrar-se a outras plataformas.

## Funcionalidades:

- **Cadastro de Usuários:** Permitir que os usuários se cadastrem e criem perfis personalizados.
- **Análise de Preferências:** Implementar algoritmos que analisem as preferências dos usuários com base em suas interações anteriores.
- **Criação e Exibição de Anúncios:** Desenvolver um sistema para criar e exibir anúncios com base nas preferências identificadas.
- **Feedback do Usuário:** Permitir que os usuários forneçam feedback sobre os anúncios que visualizaram, melhorando a personalização.
- **Persistência de Dados:** Utilizar serialização local para armazenar os dados dos usuários e seus comportamentos em um arquivo `.txt`.
- **Interface Gráfica:** Desenvolver uma interface com Java Swing que seja intuitiva e responsiva.
- **Histórico de Interações:** Manter um registro do histórico de visualizações dos usuários para análises futuras.
- **Configurações de Privacidade:** Permitir que os usuários gerenciem suas configurações de privacidade e decidam quais dados desejam compartilhar.
- **Relatórios:** Analistas autorizados podem gerar relatórios sobre os anúncios e as preferências de usuários.

## Desenvolvimento:

### Classes:

```mermaid
classDiagram
    class Usuario {
        +String nome
        +String email
        +String senha
        +boolean isAdm
        +void adicionarUsuario()
        +void removerUsuario()
        +void adicionarAdm()
        +void removerAdm()
        +boolean autenticarUsuario()
    }

    class Preferencia {
        <<abstract>>
        +String tag
        +void adicionarPreferencia()
        +void removerPreferencia()
    }

    class Gosto {
        +String tag / herança
        +List<String> gostos
        +void adicionarPreferencia() / Override
        +void removerPreferencia() / Override
    }

    class Desgosto {
        +String tag / herança
        +List<String> desgostos
        +void adicionarPreferencia() / Override
        +void removerPreferencia() / Override
    }
    
    class Anuncio{
		    +String descricao
		    +String titulo
		    +String imagem
		    +String[] tags
		    +void criarAnuncio()
    }

    Usuario --> Preferencia : "contém"
    Preferencia <|-- Gosto : "herda"
    Preferencia <|-- Desgosto : "herda"

```

### Fluxo:

```mermaid

flowchart TD
    A[Início]
    B[Registro de usuário]
    C[Login de usuário]
    D[Registro de adm]
    E[Login de adm]
    F[Ver anúncios]
    G[Criar anúncios]
    H[Ver estatísticas]
	  
    A --> B
    A --> C
		A --> D
		A --> E
		B --> C
		D --> E
		C --> F
		C --> G
		E --> H
```

### Uso:

```mermaid
flowchart TD
    A[Usuario]
    C[Registrar Usuário]
    D[Login de Usuário]
    E[Adicionar Preferência]
    F[Remover Preferência]
    G[Adicionar Usuário]
    H[Remover Usuário]
    I[Adicionar Adm]
    J[Remover Adm]
    K[Usuario não logado]
    L[Adicionar Anúncio]
    
    K --> C
    K --> D
    A --> E
    A --> F
    A-->L

    K --> G
    K --> H
    K --> I
    K --> J
```

# Manual do Usuário

## Visão Geral
Este aplicativo permite que os usuários e administradores gerenciem registros de anúncios, realizem login e registro, e visualizem anúncios filtrados com base em preferências. O aplicativo é dividido em duas interfaces principais: a interface do usuário comum e a interface do administrador.

## 1. Tela Inicial (HomeView)

### Funcionalidades
A tela inicial oferece opções para registro e login de usuários e administradores. 

### Como Usar
- **Registrar Usuário**: Clique no botão "Registrar Usuário" para abrir a janela de registro.
- **Login Usuário**: Clique no botão "Login Usuário" para abrir a janela de login.
- **Registrar Administrador**: Clique no botão "Registrar Administrador" para abrir a janela de registro de administradores.
- **Login Administrador**: Clique no botão "Login Administrador" para abrir a janela de login para administradores.

### Registro de Usuário
1. Preencha os campos **Nome**, **Email** e **Senha**.
2. Clique em **Cadastrar**. Uma mensagem será exibida confirmando o cadastro.

### Login de Usuário
1. Insira seu **Email** e **Senha**.
2. Clique em **Login**. Se o login for bem-sucedido, você será redirecionado para a tela de visualização de anúncios.

### Registro de Administrador
1. Preencha os campos **Nome**, **Email** e **Senha**.
2. Clique em **Cadastrar**. Uma mensagem de confirmação será exibida.

### Login de Administrador
1. Insira seu **Email** e **Senha**.
2. Clique em **Login**. Se o login for bem-sucedido, você será redirecionado para a tela do painel do administrador.

---

## 2. Tela de Visualização de Anúncios (AnunciosView)

### Funcionalidades
Esta tela permite que os usuários visualizem anúncios filtrados com base em suas preferências.

### Como Usar
- **Navegar Anúncios**: Use os botões **Próximo** e **Anterior** para navegar entre os anúncios.
- **Saber Mais**: Clique em **Saber Mais** para ver os detalhes de um anúncio.
- **Não Tenho Interesse**: Clique em **Não Tenho Interesse** para informar que você não está interessado no anúncio atual.

### Detalhes do Anúncio
Ao clicar em **Saber Mais**, uma nova janela será aberta mostrando o título, descrição e imagem do anúncio.

---

## 3. Painel do Administrador (AdminView)

### Funcionalidades
A interface do administrador permite adicionar novos anúncios e gerar relatórios.

### Como Usar
- **Adicionar Anúncio**: Clique no botão **Adicionar Anúncio** para abrir a janela de adição de anúncios.
- **Gerar Relatórios**: Clique nos botões de relatório para gerar relatórios de anúncios, usuários, gostos e desgostos.

### Adicionar Anúncio
1. Preencha os campos **Título**, **Descrição**, e **Tags** (separadas por vírgula).
2. Clique em **Selecionar Imagem** para escolher uma imagem do seu sistema.
3. Clique em **Adicionar** para salvar o anúncio.

