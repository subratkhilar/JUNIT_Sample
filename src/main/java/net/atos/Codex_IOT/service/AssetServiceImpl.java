package net.atos.Codex_IOT.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import net.atos.Codex_IOT.dao.AssetDao;
import net.atos.Codex_IOT.mapper.AssetMapper;
import net.atos.Codex_IOT.pojo.Asset;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public class AssetServiceImpl implements AssetService {
	
	private static final Logger logger = Logger.getLogger(AssetServiceImpl.class); // Apache
	// logger

	@Autowired
	AssetDao astdao;
	@Autowired
	AssetMapper astmaper;
	
	@Override
	public List<Asset> getassetList() {
		logger.info("in asset service dao impl");
		
		return astdao.getassetList();
	}



	@Override
	public Asset getAssetbyId(String assetId) {
		logger.info("in user asset dao impl");
		return astdao.getAssetbyId(assetId);
	}

	@Override
	public boolean deleteAssetbyId(String assetId) {
		logger.info("in Asset service dao impl");
		Asset asset=astdao.getAssetbyId(assetId);
		if(asset!=null)
			return astdao.deleteAssetbyId(asset);
		return false;
	}

	@Override
	public void updateasset(Asset asset) {
		this.astdao.updateasset(asset);
	}

	@Override
	public Asset addAsset(Asset asset) {	
		logger.info("inside add asset");
	//return astmaper.mapAssetToAssetModel(this.astdao.addAsset(this.astmaper.mapAssetModelToAssetPojo(asset)));
		return astdao.addAsset(asset);		
	}



	@Override
	public List<Asset> getAssetbyprojId(String projectId) {
	
		return astdao.getAssetbyprojId(projectId);
	}



	@Override
	public void updateAssetActiveState(String assetId) {
		logger.info("inside updateSensorActiveState()");
		
		Asset asset=astdao.getAssetbyId(assetId);
		if (asset.isActive()){
			asset.setActive(false);
			
		}
		else {
			asset.setActive(true);
		}
		asset.setUpdatedDate(new Date());
		astdao.updateasset(asset);
		
	}



	@Override
	public List<Asset> getallassetbycustomerid(long customerId) {
		// TODO Auto-generated method stub
		return  astdao.getallassetbycustomerid(customerId);
	}



	@Override
	public List<Asset> getallassetbycustid(long customerId) {
		logger.info("in daoimpl of all asett by cust id");
		return astdao.getallassetbycustid(customerId);
	}



	@Override
	public List<Asset> getallAssetbyprojId(String projectId) {
		return astdao.getallAssetbyprojId(projectId);
	}



	





	/*@Override
	public Asset getAssetbyprojId(long projectId) {
		logger.info("in user asset getting by projid dao impl");
		return astdao.getAssetbyprojId(projectId);
	}*/

	
}
