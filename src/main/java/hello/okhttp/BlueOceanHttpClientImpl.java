package hello.okhttp;

import hello.pojo.JWTTokenHeader;
import hello.utils.JWTUtils;
import hello.utils.OkHttpClientUtil;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @创建人 sgwang
 * @name BlueOceanHttpClientImpl
 * @user Anti
 * @创建时间 2020/3/4
 * @描述
 */
public class BlueOceanHttpClientImpl implements BlueOceanHttpClient {

    public static void main(String[] args) throws IOException {
        BlueOceanHttpClient blueOceanHttpClient = new BlueOceanHttpClientImpl();

        JWTTokenHeader jwtTokenHeader = JWTUtils.getToken();
        System.err.println(jwtTokenHeader);

        String url = "http://118.31.54.101:8080/blue/rest/organizations/jenkins/pipelines/";
        blueOceanHttpClient.doGet(url, jwtTokenHeader.getxBlueOceanJWT());
    }


    @Override
    public void doGet(String getURL, String accessToken) throws IOException {
        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance();

        // 发送请求前，先设置token值
        Request request = new Request.Builder()
                .url(getURL)
//                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .build();
        System.err.println(request.headers().toString());

        // 如果请求响应码 == 401，会调用authenticate接口，进行重新请求；
        okHttpClient
                .newBuilder()
                .authenticator(
                        new Authenticator() {
                            @Nullable
                            @Override
                            public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                                // 重新获取token
                                JWTTokenHeader jwtTokenHeader = JWTUtils.getToken();
                                String blueOceanToken = jwtTokenHeader.getxBlueOceanJWT();
                                System.err.println("authenticate access token : " + blueOceanToken);

                                Request authReq = response.request();
                                authReq.newBuilder().header("Authorization", "Bearer " + blueOceanToken).build();
                                System.err.println("authenticate headers: " + authReq.headers().toString());

                                return authReq;
                            }
                        }
                )
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }
}
