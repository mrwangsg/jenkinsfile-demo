package hello.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
     * 将日期格式均为ISO 8601格式："YYYY-MM-DDTHH:MM:SSZ" 转化为 UTC时间格式："yyyy-MM-ddTHH:mm:ssXXX"
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
