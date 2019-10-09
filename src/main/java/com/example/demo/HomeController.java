package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/")
    public String homepage(Model model){
        model.addAttribute("books", bookRepository.findAll());
//        model.addAttribute("authors", authorRepository.findAll());

        return "index";
    }

    @GetMapping ("/addbook")
    public String bookform (Model model){
        model.addAttribute("book", new Book());
        return "bookform";
    }


    @PostMapping("/process_book")
    public String processBookForm(@Valid Book book, BindingResult result){
        if (result.hasErrors()){
            return "bookform";
        }
        bookRepository.save(book);

        return "redirect:/booklist";
    }

    @RequestMapping("/booklist")
    public String bookList(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }


    @GetMapping("/addauthor")
    public String authorForm(Model model){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("author", new Author());
        return "authorform";
    }

    @PostMapping("/process_author")
    public String processAuthorForm(@Valid Author author, BindingResult result){
        if (result.hasErrors()){
            return "authorform";
        }
        authorRepository.save(author);

        return "redirect:/authorlist";
    }

    @RequestMapping("/authorlist")
    public String authorList(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authorlist";
    }

    @RequestMapping("/detail_book/{id}")
    public String showBook(@PathVariable("id") long id, Model model){
        model.addAttribute("book", bookRepository.findById(id).get());
        return "showbook";
    }

    @RequestMapping("/update_book/{id}")
    public String updateBook(@PathVariable("id") long id, Model model){
        model.addAttribute("book", bookRepository.findById(id).get());
        return "bookform";
    }

    @RequestMapping("/delete_book/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookRepository.deleteById(id);
//        return "index";
        return "redirect:/";
    }

    @RequestMapping("/detail_author/{id}")
    public String showAuthor(@PathVariable("id") long id, Model model){
        model.addAttribute("author", authorRepository.findById(id).get());
        return "showauthor";
    }

    @RequestMapping("/update_author/{id}")
    public String updateAuthor(@PathVariable("id") long id, Model model){
        model.addAttribute("author", authorRepository.findById(id).get());
        model.addAttribute("books",bookRepository.findAll());
        return "authorform";
    }

    @RequestMapping("/delete_author/{id}")
    public String deleteAuthor(@PathVariable("id") long id){
        authorRepository.deleteById(id);
//        return "index";
        return "redirect:/";
    }
}
