package hello.utils;

import hello.pojo.JWTTokenHeader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建人 sgwang
 * @name JWTUtils
 * @user Anti
 * @创建时间 2020/3/4
 * @描述
 */
public class JWTUtils {
    public final static String Token_URL = "http://118.31.54.101:8080/jwt-auth/token";

    public final static String Header_Date = "Date";
    public final static String Header_XContentTypeOptions = "X-Content-Type-Options";
    public final static String Header_XBlueOceanJWT = "X-BLUEOCEAN-JWT";
    public final static String Header_Server = "Server";

    /**
     * 获取token
     * @return JWTTokenHeader
     * @throws IOException
     */
    public static JWTTokenHeader getToken() throws IOException {
        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance();

        Request req = new Request.Builder().url(JWTUtils.Token_URL).build();
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

    /**
     * 将日期格式均为ISO 8601格式："YYYY-MM-DDTHH:MM:SSZ" 转化为 UTC时间格式："yyyy-MM-ddTHH:mm:ssXXX"
     *
     * @param dateStr
     * @return String
     */
    public static String formatDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        return sdf.format(new Date(dateStr));
    }

    public static void main(String[] args) {
        String dateStr = "Wed, 04 Mar 2020 09:03:02 GMT";
        System.out.println(JWTUtils.formatDate(dateStr));
    }
}
