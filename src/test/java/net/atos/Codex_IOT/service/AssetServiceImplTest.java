package net.atos.Codex_IOT.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.AssetDao;
import net.atos.Codex_IOT.mapper.AssetMapper;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceImplTest {
	@Mock
	private AssetDao astdao;
	@Mock
	AssetMapper astmaper;

	@InjectMocks
	private AssetService assertService = new AssetServiceImpl();
	Asset asset = new Asset();

	@Before
	public void beforeTest() {
		asset.setAssetId("As001");
		asset.setAssetDesc("test1");
		asset.setActive(true);
		asset.setAssetIpAddress("162.16.10.2");
		asset.setAssetName("test");
		asset.setAssetProtocol("protocol1");
		asset.setAssetSerialNumber("Ts001");
		asset.setCreatedDate(new Date());
		asset.setUpdatedDate(new Date());
		Project project = new Project();
		project.setProjectId("pr001");
		project.setProjectDescription("CodexIOT");
		project.setProjectLocation("Bangalore");
		Customer customer = new Customer();
		customer.setCustomerId(1001L);
		customer.setCustomerName("Hari");
		customer.setCustomerNumbere(1);
		customer.setActive(true);
		project.setCustomer(customer);
		asset.setProject(project);
	}

	@Test
	public void testGetassetList() {
		List<Asset> list = new ArrayList<Asset>();
		list.add(asset);
		when(astdao.getassetList()).thenReturn(list);
		List<Asset> results = assertService.getassetList();
		assertEquals(1, results.size());

	}

	@Test
	public void testGetAssetbyId() {
		when(astdao.getAssetbyId("As001")).thenReturn(asset);
		Asset newAsset = assertService.getAssetbyId("As001");
		// Validation
		assertNotNull(newAsset);
		assertEquals("As001", newAsset.getAssetId());

	}

	@Test
	public void testDeleteAssetbyId() {
		when(astdao.getAssetbyId("As001")).thenReturn(asset);
		Asset assetid = astdao.getAssetbyId("As001");
		boolean result = assertService.deleteAssetbyId("As001");
		boolean flag = astdao.deleteAssetbyId(assetid);
		assertFalse(result);
		// assertTrue(flag);

	}

	@Test
	public void testDeleteAssetbyId_failure() {
		when(astdao.deleteAssetbyId(asset)).thenReturn(true);
		boolean result = assertService.deleteAssetbyId("As001");
		assertFalse(result);

		// assertTrue(flag);

	}

	@Test
	public void testUpdateasset() {
		when(astdao.getAssetbyId("As001")).thenReturn(asset);
		assertService.updateasset(asset);
		doNothing().when(astdao).updateasset(asset);
	}

	@Test
	public void testAddAsset() {
		// Mockito expectations
		when(astdao.addAsset(any(Asset.class))).thenReturn(asset);
		// Execute the method being tested
		Asset newAsset = assertService.addAsset(asset);
		// Validation
		assertNotNull(newAsset);
		assertEquals(asset.getAssetId(), newAsset.getAssetId());
		verify(astdao).addAsset(any(Asset.class));
	}

	@Test
	public void testGetAssetbyprojId() {
		List<Asset> assetList = new ArrayList<>();
		assetList.add(asset);
		when(astdao.getAssetbyprojId("pr001")).thenReturn(assetList);
		List<Asset> newAssetList = assertService.getAssetbyprojId("pr001");
		assertNotNull(newAssetList);
		assertEquals("As001", newAssetList.get(0).getAssetId());
		assertEquals(assetList, newAssetList);

	}

	@Test
	public void testUpdateAssetActiveState() {
		asset.setActive(false);
		when(astdao.getAssetbyId("As001")).thenReturn(asset);
		assertService.updateAssetActiveState("As001");
		doNothing().when(astdao).updateasset(asset);

	}

	@Test
	public void testUpdateAssetActiveState_Active() {
		when(astdao.getAssetbyId("As001")).thenReturn(asset);
		assertService.updateAssetActiveState("As001");
		doNothing().when(astdao).updateasset(asset);

	}

	@Test
	public void testGetallassetbycustomerid() {
		List<Asset> assetList = new ArrayList<>();
		assetList.add(asset);
		when(astdao.getallassetbycustomerid(1001L)).thenReturn(assetList);
		List<Asset> newAssetList = assertService.getallassetbycustomerid(1001L);
		assertNotNull(newAssetList);
		assertEquals("As001", newAssetList.get(0).getAssetId());
		assertEquals(assetList, newAssetList);
	}

	@Test
	public void testGetallassetbycustid() {
		List<Asset> assetList = new ArrayList<>();
		assetList.add(asset);
		when(astdao.getallassetbycustid(1001L)).thenReturn(assetList);
		List<Asset> newAssetList = assertService.getallassetbycustid(1001L);
		assertNotNull(newAssetList);
		assertEquals("As001", newAssetList.get(0).getAssetId());
		assertEquals(assetList, newAssetList);
	}

	@Test
	public void testGetallAssetbyprojId() {
		List<Asset> assetList = new ArrayList<>();
		assetList.add(asset);
		when(astdao.getallAssetbyprojId("pr001")).thenReturn(assetList);
		List<Asset> newAssetList = assertService.getallAssetbyprojId("pr001");
		assertNotNull(newAssetList);
		assertEquals("As001", newAssetList.get(0).getAssetId());
		assertEquals(assetList, newAssetList);
	}

}
