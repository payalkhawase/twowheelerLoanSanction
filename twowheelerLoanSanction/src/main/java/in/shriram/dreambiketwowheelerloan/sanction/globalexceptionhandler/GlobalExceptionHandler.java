package in.shriram.dreambiketwowheelerloan.sanction.globalexceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.shriram.dreambiketwowheelerloan.sanction.dto.CustomerResponse;
import in.shriram.dreambiketwowheelerloan.sanction.exceptions.IncorrectPasswordException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<CustomerResponse> incorrectPassword(IncorrectPasswordException ip){
		
		String msg=ip.getMessage();
		
		CustomerResponse cr=new CustomerResponse(msg, new Date());
	
		return new ResponseEntity<CustomerResponse>(cr,HttpStatus.BAD_REQUEST);
	}

}
