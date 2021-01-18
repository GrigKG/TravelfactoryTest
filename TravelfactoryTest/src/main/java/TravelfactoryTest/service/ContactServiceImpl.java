package TravelfactoryTest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TravelfactoryTest.dao.CampaignRepository;
import TravelfactoryTest.dao.ContactRepository;
import TravelfactoryTest.dto.ContactDTO;
import TravelfactoryTest.model.Campaign;
import TravelfactoryTest.model.Contact;
import company.exception.CampaignNotFoundException;
import company.exception.ContactConflictException;
import company.exception.ContactEmailException;
import company.exception.ContactPhoneException;

@Service
public class ContactServiceImpl implements ContactService {

	private static final String mailMatches = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$";
	private static final String phoneMatches = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	CampaignRepository campaignRepository;

	@Transactional
	@Override
	public boolean createPartner(String campaignId, ContactDTO contactDTO) {
		
		String mail = contactDTO.getEmail();
		
		if(!mail.matches(mailMatches)) {
			throw new ContactEmailException(mail);
		}
		
		if(contactDTO.getPhone().matches(phoneMatches)) {
			throw new ContactPhoneException(contactDTO.getPhone());
		}
		
		if (contactRepository.existsById(mail)) {
			throw new ContactConflictException(mail);
		}
			
		Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException());
	
		Contact contact = new Contact(mail, contactDTO.getFirstName(), contactDTO.getName(), contactDTO.getPhone(), campaign);
		
		if (!campaign.addContact(contact)) {
			throw new RuntimeException();
		};
		contactRepository.save(contact);
		//campaignRepository.save(campaign);
		return true;
	}

}
