package TravelfactoryTest.service;

import org.springframework.stereotype.Service;

import TravelfactoryTest.dto.CampaignDTO;

@Service
public interface CampaignService {
	boolean createCampaign(String id, CampaignDTO campaignDto);
	CampaignDTO getCampaign(String id);
	CampaignDTO updateCampaign(String id, CampaignDTO campaignDto);
	boolean removeCampaign(String id);
}
