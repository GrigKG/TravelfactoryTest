package company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ContactConflictException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8865719398771533703L;
	
	public ContactConflictException(String mail) {
		super("Contact with mail " + mail + " conflict");
	}
}
