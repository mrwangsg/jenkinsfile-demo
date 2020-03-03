package hello.utils;

import com.offbytwo.jenkins.model.FolderJob;
import hello.jenkins.type.JobsClassType;

/**
 * @创建人 sgwang
 * @name JenkinsUtils
 * @user Anti
 * @创建时间 2020/3/2
 * @描述
 */
public class JenkinsUtils {
    public final static String ServerIP = "118.31.54.101";
    public final static String LoginTokenURL = "http://" + ServerIP + ":8080";

    public final static String jobURL = "/job/";

    public final static String UserName = "sgwang";
    public final static String PassWord = "sgwang";

    public static FolderJob buildFolderJobByProject(String projectName) {
        return new FolderJob(projectName, jobURL + projectName);
    }

    public static boolean ifExistBranch(String jobClass) {
        boolean flag = false;

        JobsClassType jobsClass = JobsClassType.getByClassNameStr(jobClass);
        if (jobClass == null){
            return flag;
        }
        switch (jobsClass) {
            case FreeStyle:
                break;
            case WorkflowMultiBranch:
                flag = true;
                break;
            default:
                break;
        }

        return flag;
    }


}
