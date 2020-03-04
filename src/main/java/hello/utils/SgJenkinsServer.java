package hello.utils;

import com.offbytwo.jenkins.JenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @创建人 sgwang
 * @name SgJenkinsServer
 * @user Anti
 * @创建时间 2020/3/3
 * @描述 双重校验锁法 单例模式. (存在DCL失效缺陷)
 */
public class SgJenkinsServer {
    private static Logger logger = LoggerFactory.getLogger(SgJenkinsServer.class);
    private static volatile JenkinsServer instance = null; // 因为初始化对象的乱序型，需要加关键字volatile

    private SgJenkinsServer() {
    }

    public static JenkinsServer getInstance() {
        if (instance == null) {
            synchronized (SgJenkinsServer.class) {
                if (instance == null) {
                    try {
                        instance = new JenkinsServer(new URI(JenkinsUtils.LoginTokenURL), JenkinsUtils.UserName, JenkinsUtils.PassWord);
                    } catch (URISyntaxException e) {
                        logger.error("SingletonJenkinsServer.getInstance(): " + e.getMessage());
                    }
                }
            }
        }
        return instance;
    }
}
