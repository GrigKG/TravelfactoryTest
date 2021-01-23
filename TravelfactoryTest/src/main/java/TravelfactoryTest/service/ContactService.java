package TravelfactoryTest.service;

import org.springframework.stereotype.Service;

import TravelfactoryTest.dto.ContactDTO;

@Service
public interface ContactService {
	boolean createPartner(String campaignId, ContactDTO dto) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException;

}
