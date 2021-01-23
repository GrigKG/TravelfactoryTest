package TravelfactoryTest.service;

import java.lang.reflect.Field;
import java.util.List;

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
import company.exception.FieldsConflictException;

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
	public boolean createPartner(String campaignId, ContactDTO contactDTO) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

		String mail = contactDTO.getEmail();

		if (!mail.matches(mailMatches)) {
			throw new ContactEmailException(mail);
		}

		if (!contactDTO.getPhone().matches(phoneMatches)) {
			throw new ContactPhoneException(contactDTO.getPhone());
		}

		if (contactRepository.existsById(mail)) {
			throw new ContactConflictException(mail);
		}

		Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException());
		List<String> mandatoryFields = campaign.getMandatoryFields();
		for (String mFld : mandatoryFields) {
			 Field fld = contactDTO.getClass().getDeclaredField(mFld);
			if (!checkMandatoryField(fld, contactDTO)) {
				throw new FieldsConflictException();
			}
		}
		Contact contact = new Contact(mail, contactDTO.getFirstName(), contactDTO.getName(), contactDTO.getPhone(),
				campaign);

		if (!campaign.addContact(contact)) {
			throw new RuntimeException();
		}
		contactRepository.save(contact);
		return true;
	}

	private boolean checkMandatoryField(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {
		String val = field.get(object).toString();
		switch (field.getName()) {
		case "email":
			return val.matches(mailMatches);
			
		case "phone":
			return val.matches(phoneMatches);	

		default:
			return (val!=null&&val.equals(""));
		}
	}
}
