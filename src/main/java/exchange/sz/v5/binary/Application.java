package exchange.sz.v5.binary;

import exchange.sz.v5.binary.config.SZExchangeConfigure;
import exchange.sz.v5.binary.gateway.SZV5MarketBootstrap;
import exchange.sz.v5.binary.gateway.SZV5MessageFacade;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuejian.sun
 * @date 2019/12/4 16:11
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {
//        new NioServer(33313).run();
//        Thread.sleep(3000);
//        NioClient client = new NioClient("101.227.110.117", 12867);
//        client.start();
//        client.logon("","Kafang","YFF_Send",5,"1.03");


        SZExchangeConfigure configure = new SZExchangeConfigure();
        configure.setReconnect(5);
        configure.setReconnectInterval(10);
        configure.setServerHost("101.227.110.117");
        configure.setServerPort(12867);
        configure.setHeartbeatInterval(10);
        configure.setPassword("");
        configure.setSenderCompId("Kafang");
        configure.setTargetCompId("YFY_Send");
        configure.setVersion("1.01");
        SZV5MarketBootstrap bootstrap = SZV5MarketBootstrap.getInstance(configure,new SZV5MessageFacade());
        Runtime.getRuntime().addShutdownHook(new Thread(bootstrap::logout));
        boolean connect = bootstrap.connect();
        if(connect){
            bootstrap.login();
        }
        new CountDownLatch(1).await();
    }
}
