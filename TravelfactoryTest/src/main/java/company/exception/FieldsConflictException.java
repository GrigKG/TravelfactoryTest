package company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class FieldsConflictException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8865719398771533703L;
	
	public FieldsConflictException() {
		super("Mandatory fields are not filled");
	}
}
