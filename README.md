# 📚 BooksApp

Um aplicativo Android desenvolvido em Kotlin com arquitetura MVVM, criado como parte da minha jornada de aprendizado. O objetivo é listar livros, marcar favoritos, ver detalhes e até remover livros da lista.

## ✨ Funcionalidades

- ✅ Listagem de livros com título, autor e gênero  
- ❤️ Marcar e desmarcar livros como favoritos  
- ⭐ Visualizar apenas os livros favoritos  
- 📝 Tela de detalhes com informações da obra  
- 🗑️ Remoção de livros com confirmação  
- 🔁 Dados gerenciados localmente via Repository  

## 🛠️ Tecnologias utilizadas

- Kotlin 💻  
- Android (View Binding + XML Layouts)  
- MVVM (Model - View - ViewModel)  
- LiveData 📡  
- Navigation Component 🔀  
- RecyclerView com Adapter e ViewHolder  
- Design moderno com categorias visuais por gênero  

## 🗂️ Estrutura do projeto

```bash
📁 app/
├── ui/                # Fragments e MainActivity
├── ViewModels/        # ViewModels (Home, Favorite, Details)
├── repository/        # BookRepository (simula banco de dados)
├── entity/            # BookEntity.kt
├── ui/adapter/        # Adapter do RecyclerView
├── ui/viewholder/     # ViewHolder personalizado
├── helper/            # Constantes globais
