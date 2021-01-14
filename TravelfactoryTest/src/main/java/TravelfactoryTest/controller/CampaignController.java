package TravelfactoryTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TravelfactoryTest.dto.CampaignDTO;
import TravelfactoryTest.service.CampaignService;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class CampaignController {

	@Autowired
	CampaignService campaignService;
	
	
	@PostMapping("/{id}")
	public boolean createCampaign(@PathVariable String id,@RequestBody CampaignDTO campaignDto) {
		return campaignService.createCampaign(id, campaignDto);
	}
	
	@GetMapping("/{id}")
	public CampaignDTO getCampaign(@PathVariable String id) {
		return campaignService.getCampaign(id);
	}
	
	@PutMapping("/{id}")
	public CampaignDTO updateCampaign(@PathVariable String id,@RequestBody CampaignDTO campaignDto) {
		return campaignService.updateCampaign(id, campaignDto);
	}
	
	@DeleteMapping("/{id}")
	public boolean removeCampaign(@PathVariable String id) {
		return campaignService.removeCampaign(id);
	}
}
