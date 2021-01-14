package TravelfactoryTest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TravelfactoryTest.dao.CampaignRepository;
import TravelfactoryTest.dto.CampaignDTO;
import TravelfactoryTest.model.Campaign;
import company.exception.CampaignNotFoundException;

@Service
public class CampanignServiceImpl implements CampaignService {

	@Autowired
	CampaignRepository campaignRepository;	
	
	@Override
	public boolean createCampaign(String id, CampaignDTO campaignDTO) {
		if (campaignRepository.existsById(id)) {
			return false;
		}
		Campaign campaign = new Campaign(id, campaignDTO.getCampaignName(), campaignDTO.getMandatoryFields());
		campaignRepository.save(campaign);
		return true;
	}

	@Override
	public CampaignDTO getCampaign(String id) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
		return new CampaignDTO(campaign.getCampaignName(), campaign.getMandatoryFields());
	}
	
	@Transactional
	@Override
	public CampaignDTO updateCampaign(String id, CampaignDTO campaignDTO) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
		campaign.setCampaignName(campaignDTO.getCampaignName());
		campaign.setMandatoryFields(campaignDTO.getMandatoryFields());
		campaignRepository.save(campaign);
		return campaignDTO;
	}
	
	@Transactional
	@Override
	public boolean removeCampaign(String id) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
		campaignRepository.delete(campaign);
		return true;
	}

}
