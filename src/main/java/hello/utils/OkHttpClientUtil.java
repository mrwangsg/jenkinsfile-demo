package hello.utils;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @创建人 sgwang
 * @name OkHttpClientUtil
 * @user Anti
 * @创建时间 2020/3/4
 * @描述 静态内部类模式 单例模式
 */
public class OkHttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);

    private OkHttpClientUtil() {
    }

    private static class InnerHolder {
        private static OkHttpClient instance = new OkHttpClient().newBuilder().build();
    }

    public static OkHttpClient getInstance() {
        return InnerHolder.instance;
    }


    public static void main(String[] args) {
        // 测试 是否实现 单例
        OkHttpClientUtil.getInstance();
        System.out.println(OkHttpClientUtil.getInstance());
        System.out.println(OkHttpClientUtil.getInstance());

        // 使用不当，会不断出现连接池，浪费资源
        OkHttpClient client01 = new OkHttpClient().newBuilder().build();
        OkHttpClient client02 = client01.newBuilder().build();
        System.out.println("连接池： " + client01.connectionPool());
        System.out.println("连接池： " + client02.connectionPool());

        OkHttpClient client03 = new OkHttpClient().newBuilder().build();
        OkHttpClient client04 = new OkHttpClient().newBuilder().build();
        System.out.println("连接池： " + client03.connectionPool());
        System.out.println("连接池： " + client04.connectionPool());
    }
}
