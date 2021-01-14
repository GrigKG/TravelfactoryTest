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

	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	CampaignRepository campaignRepository;

	@Transactional
	@Override
	public boolean createPartner(String campaignId, ContactDTO contactDTO) {
		
		String mail = contactDTO.getEmail();
		
		if (contactRepository.existsById(mail)) {
			throw new ContactConflictException(mail);
		}
		
		if(!mail.matches("^ [\\w - .] + @ ( [\\w -] + .) + [\\w -] {2,4} $ ")) {
			throw new ContactEmailException(mail);
		}
		
		if(contactDTO.getPhone().matches("^ [+] * [(] {0,1} [0-9] {1,4} [)] {0,1} [- \\ s \\ ./ 0-9] * $")) {
			throw new ContactPhoneException(contactDTO.getPhone());
		}
		
		Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException());
	
		Contact contact = new Contact(contactDTO.getName(), contactDTO.getFirstName(), contactDTO.getEmail(), contactDTO.getPhone(), campaign);
		
		if (!campaign.addContact(contact)) {
			throw new RuntimeException();
		};
		contactRepository.save(contact);
		campaignRepository.save(campaign);
		return true;
	}

}
