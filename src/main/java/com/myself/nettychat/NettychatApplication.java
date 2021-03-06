package com.myself.nettychat;

import com.myself.nettychat.config.NettyConfig;
import com.myself.nettychat.config.NettyTcpConfig;
import com.myself.nettychat.config.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NettychatApplication {

	public static void main(String[] args) throws Exception{
//		SpringApplication.run(NettychatApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(NettychatApplication.class, args);
		NettyConfig nettyConfig = context.getBean(NettyConfig.class);
		NettyTcpConfig nettyTcpConfig = context.getBean(NettyTcpConfig.class);
		TCPServer tcpServer = context.getBean(TCPServer.class);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Web端Netty通信服务端启动成功！端口：8090");
					tcpServer.startWeb();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("TCP端Netty通信服务端启动成功！端口：8092");
					tcpServer.startTcp();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}

}
