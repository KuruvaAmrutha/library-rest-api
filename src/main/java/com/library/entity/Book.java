package com.library.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookId;
	@NotNull(message="Book Name must not be Null")
	@NotEmpty(message="Book Name must not Empty")
	@NotBlank(message="Book Name must not be Blank,Not be Null Neither Empty")
	private  String bookName;
	private  String category;
	@Size(min=3,max=50,message="Author name must be between 3 and 50")
	private  String author;
	@Min(value=100,message="Minimum price Must 100 or Above")
	@Max(value=10000,message="Maximum price Must 10000 or Below")
	private  double price;
	@Email(message="Email must be valid")
	@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message="Invalid")
	private String email;
	@Positive(message="Must be greater than 0")
//	@PositiveOrZero
//	@Negative
//	@NegativeOrZero
	private Integer quantity;
	@Positive(message="phone number must>=0")
	private Long phone;
	//@Past(message="past date only allowed")
	//@Future(message="future date only allowed")
	@PastOrPresent
	//@FutureOrPresent
	private LocalDate date;
	

}
