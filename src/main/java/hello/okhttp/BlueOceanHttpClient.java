package hello.okhttp;

import hello.pojo.JWTTokenHeader;

import java.io.IOException;

/**
 * @创建人 sgwang
 * @name BlueOceanHttpClient
 * @user Anti
 * @创建时间 2020/3/4
 * @描述
 */
public interface BlueOceanHttpClient {
    JWTTokenHeader getToken(String tokenURL) throws IOException;
    void doGet(String accessToken) throws IOException;
}
