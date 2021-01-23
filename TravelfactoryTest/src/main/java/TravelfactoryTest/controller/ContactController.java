package TravelfactoryTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TravelfactoryTest.dto.ContactDTO;
import TravelfactoryTest.service.ContactService;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class ContactController {
	

	@Autowired
	ContactService contactService;
	
	@PostMapping("/{campaignId}/registration")
	public boolean createPartner(@PathVariable String campaignId,@RequestBody ContactDTO dto) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return contactService.createPartner(campaignId, dto);
	}
}
