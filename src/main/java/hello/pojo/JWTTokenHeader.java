package hello.pojo;

/**
 * @创建人 sgwang
 * @name JWTTokenHeader
 * @user Anti
 * @创建时间 2020/3/4
 * @描述
 */
public class JWTTokenHeader {
    private String date;
    private String xContentTypeOptions;
    private String xBlueOceanJWT;
    private String server;

    @Override
    public String toString() {
        return super.toString()
                + ", date: " + date
                + ", xContentTypeOptions: " + xContentTypeOptions
                + ", xBlueOceanJWT: " + xBlueOceanJWT
                + ", server: " + server;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getxContentTypeOptions() {
        return xContentTypeOptions;
    }

    public void setxContentTypeOptions(String xContentTypeOptions) {
        this.xContentTypeOptions = xContentTypeOptions;
    }

    public String getxBlueOceanJWT() {
        return xBlueOceanJWT;
    }

    public void setxBlueOceanJWT(String xBlueOceanJWT) {
        this.xBlueOceanJWT = xBlueOceanJWT;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
