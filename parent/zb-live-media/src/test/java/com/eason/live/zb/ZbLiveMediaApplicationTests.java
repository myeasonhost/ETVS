package com.eason.live.zb;

import com.eason.live.zb.service.impl.TencentLiveUrlServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZbLiveMediaApplicationTests {

	@Autowired
	private TencentLiveUrlServiceImpl liveUrlService;

	@Test
	public void contextLoads() throws  Exception{
		System.out.println(liveUrlService.getPushUrl("1"));
		System.out.println(liveUrlService.getPlayUrl("1"));
	}

}
