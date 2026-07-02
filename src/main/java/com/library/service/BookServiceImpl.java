package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.repo.BookRepo;


@Service
public class BookServiceImpl  implements BookService {
	
	@Autowired
	BookRepo brepo;
	@Override
	public Book saveBook(Book book) {
		return brepo.save(book);
	}
	@Override
	public List<Book> getAllBooks()
	{
		return brepo.findAll();
	}
	@Override
	public Book updateBook(Integer id, Book updatedBook)
	{
		Book existingBook=brepo.findById(id).orElse(null);
		if(existingBook!=null)
		{
			if(updatedBook!=null)
			existingBook.setBookName(updatedBook.getBookName());
			existingBook.setCategory(updatedBook.getCategory());
			existingBook.setAuthor(updatedBook.getAuthor());
			existingBook.setPrice(updatedBook.getPrice());
		}
		return brepo.save(existingBook);
	}
	@Override
	public Book updateBookPartially(Integer id, Book updatedBook) 
	{

		Book existingBook=brepo.findById(id).orElse(null);
		if(existingBook!=null)
		{
			if(updatedBook.getBookName()!=null)
			existingBook.setBookName(updatedBook.getBookName());
			if(updatedBook.getCategory()!=null)
			existingBook.setCategory(updatedBook.getCategory());
			if(updatedBook.getAuthor()!=null)
			existingBook.setAuthor(updatedBook.getAuthor());
			if(updatedBook.getPrice()!=0)
			existingBook.setPrice(updatedBook.getPrice());
		}
	
		return brepo.save(existingBook);
	}
	@Override
	public void deleteBookById(Integer id) {
		brepo.deleteById(id);
    }
	@Override
	public Book getBookById(Integer id) {
		
		return brepo.findById(id).orElse(null);
	}

}