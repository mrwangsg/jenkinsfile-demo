package hello.okhttp;

import hello.pojo.JWTTokenHeader;
import hello.utils.JWTUtils;
import hello.utils.OkHttpClientUtil;
import okhttp3.*;

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

        JWTTokenHeader jwtTokenHeader = blueOceanHttpClient.getToken(JWTUtils.Token_URL);
        System.err.println(jwtTokenHeader);

        blueOceanHttpClient.doGet(jwtTokenHeader.getxBlueOceanJWT());
    }

    public JWTTokenHeader getToken(String tokenURL) throws IOException {
        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance();

        Request req = new Request.Builder().url(tokenURL).build();
        Response resp = okHttpClient.newCall(req).execute();

        if (!resp.isSuccessful()) {
            throw new IOException("Server error: " + resp);
        }

        // 保存信息
        JWTTokenHeader jwtTokenHeader = new JWTTokenHeader();
        jwtTokenHeader.setDate(JWTUtils.formatDate(resp.header(JWTUtils.Header_Date)));
        jwtTokenHeader.setxContentTypeOptions(resp.header(JWTUtils.Header_XContentTypeOptions));
        jwtTokenHeader.setxBlueOceanJWT(resp.header(JWTUtils.Header_XBlueOceanJWT));
        jwtTokenHeader.setServer(resp.header(JWTUtils.Header_Server));

        return jwtTokenHeader;
    }

    public void doGet(String accessToken) throws IOException {
        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance();
        okHttpClient = okHttpClient.newBuilder().authenticator(
                new Authenticator() {
                    public Request authenticate(Route route, Response response) throws IOException {
                        return response.request().newBuilder().header("Authorization", "Bearer " + accessToken).build();
                    }
                }).build();

        String url = "http://118.31.54.101:8080/blue/rest/organizations/jenkins/pipelines/";

        Request request = new Request.Builder().url(url).build();

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
