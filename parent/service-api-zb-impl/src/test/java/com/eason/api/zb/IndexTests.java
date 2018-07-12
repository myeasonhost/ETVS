package com.eason.api.zb;

import com.eason.api.zb.dao.IndexBannerDao;
import com.eason.api.zb.dao.MsgNotificationDao;
import com.eason.api.zb.model.SaveMongoEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTests {
	@Autowired
	private IndexBannerDao indexBannerDao;
	@Autowired
	private MsgNotificationDao msgNotificationDao;
	@Autowired
	private SaveMongoEventListener saveMongoEventListener;

	@Test
	public void addIndexBanner() {

	}

	@Test
	public void addMsgNotification() {

	}
}
