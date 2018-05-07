package com.ericwyn.leablog;

import com.ericwyn.ezerver.SimpleHttpServer;
import com.ericwyn.ezerver.expection.WebServerException;
import com.ericwyn.leablog.controller.PageController;
import com.ericwyn.leablog.controller.ResController;

public class LeablogApplication {

	public static void main(String[] args) throws WebServerException {

		// 新建 SimpleHttpServer Builder
		SimpleHttpServer.Builder builder = new SimpleHttpServer.Builder()
//				.allowDebug()
				.setServerPort(2334);

		// 载入实现了 loadHandleMethod 接口的 Controller ，作为 Controller 层
		PageController pageController = new PageController();
		ResController resController = new ResController();

		pageController.loadHandMethod(builder);
		resController.loadHandMethod(builder);

		// 新建 SimpleHttpServer
		SimpleHttpServer server = builder.build();

		// 开启 server
		server.start();

	}
}
