package com.minesweeper.repo;

import com.minesweeper.map.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试读写模块
 * @author sssunki
 */
public class TestRepository {
	@Test
	public void testGetInstance() {
		Repository instance = Repository.getInstance();
		Assert.assertNotNull(instance);
	}

	@Test
	public void testSaveGameContext() {
		Repository instance = Repository.getInstance();
		Map map = new Map();
		Assert.assertTrue(instance.saveGameContext(map));
	}

	@Test
	public void testReadContext() {
		Repository instance = Repository.getInstance();
		Map map = new Map();
		Assert.assertTrue(instance.readContext(map));
	}

	@Test
	public void testReadAllHistory() {
		Repository instance = Repository.getInstance();
		Assert.assertEquals(instance.readAllHistory(), 0);
	}

	@Test
	public void testReadSuccessHistory() {
		Repository instance = Repository.getInstance();
		Assert.assertEquals(instance.readSuccessHistory(), 0);
	}

	@Test
	public void testUpdateHistory() {
		Repository instance = Repository.getInstance();
		Assert.assertTrue(instance.updateHistory());
		Assert.assertEquals(instance.readSuccessHistory(), 1);
	}

	@Test
	public void testUpdateAllHistory() {
		Repository instance = Repository.getInstance();
		Assert.assertTrue(instance.updateAllHistory());
		Assert.assertEquals(instance.readAllHistory(), 1);
	}

	@Test
	public void testClearHistory() {
		Repository instance = Repository.getInstance();
		Assert.assertTrue(instance.clearHistory());
		Assert.assertEquals(instance.readSuccessHistory(), 0);
		Assert.assertEquals(instance.readAllHistory(), 0);
	}
}
