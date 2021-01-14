package TravelfactoryTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import TravelfactoryTest.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, String> {

}
