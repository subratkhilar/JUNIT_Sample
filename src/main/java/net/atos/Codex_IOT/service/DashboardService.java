package net.atos.Codex_IOT.service;

public interface DashboardService {

	public int getNoOfProjects(String customerId);

	public int getNoOfAssets(String customerId);

	public int getNoOfSensors(String customerId);

	public int getNoOfAssetsByProjectId(String projectId);

	public int getNoOfSensorsByAssetId(String assetId);
}
