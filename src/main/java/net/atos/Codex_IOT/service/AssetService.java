package net.atos.Codex_IOT.service;

import java.util.List;

import net.atos.Codex_IOT.pojo.Asset;

public interface AssetService {
	
	
		
	
	List<Asset> getassetList();

	Asset getAssetbyId(String assetId);

	boolean deleteAssetbyId(String assetId);

	void updateasset(Asset asset);

	public Asset addAsset(Asset assetdata);

	public List<Asset> getAssetbyprojId(String projectId);
	
	public void updateAssetActiveState(String assetId);

	List<Asset> getallassetbycustomerid(long customerId);

	List<Asset> getallassetbycustid(long customerId);

	List<Asset> getallAssetbyprojId(String projectId);

}
