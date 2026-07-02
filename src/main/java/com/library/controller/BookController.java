package com.library.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.service.BookService;

import jakarta.validation.Valid;

//@Controller
//@ResponseBody
@RestController //@Controller+@ResponseBody and enable rest api
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	BookService bservice;
	
	
//	@GetMapping("/dummy")
//	public String dummy()
//	{
//		return "Hello World";
//	}
	
	@PostMapping("/add") //POST:/api/books/add
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) 
	{
		HttpHeaders header=new HttpHeaders();
		header.add("library","It has some API info");
		return  new ResponseEntity<>(bservice.saveBook(book),header,HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<Book>> viewAllBooks()
	{
		return ResponseEntity.ok(bservice.getAllBooks());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id,@RequestBody Book updatedBook)
	{
		return ResponseEntity
				.status(200)
				.body(bservice.updateBook(id, updatedBook));
	}
	@PatchMapping("/update/{id}")
	public Book updatePartialBook(@PathVariable Integer id,@RequestBody Book updatedBook)
	{
		return bservice.updateBookPartially(id, updatedBook);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Integer id)
	{
	   bservice.deleteBookById(id);
	   return ResponseEntity
			   .noContent()
			  .build();//It follows Builder Design Pattern
	}
	@GetMapping("/{id}")
	public ResponseEntity<Book> viewBook(@PathVariable Integer id) throws BookNotFoundException
	{
		Book book=bservice.getBookById(id);
		if(book==null)
		{
			throw new BookNotFoundException("Book Not Found With Id: "+id);
			
		}
		return ResponseEntity
				.ok(book);
				
	} 
//	@ExceptionHandler(BookNotFoundException.class)
//	public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex)
//	{
//		return ResponseEntity
//				.status(404)
//				.body(ex.getMessage());
//				
//	}

}