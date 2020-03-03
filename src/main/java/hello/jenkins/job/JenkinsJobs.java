package hello.jenkins.job;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.FolderJob;
import com.offbytwo.jenkins.model.Job;
import hello.utils.JenkinsUtils;
import hello.utils.SgJenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name JenkinsJobs
 * @user Anti
 * @创建时间 2020/3/2
 * @描述 以"normal-demo" || "pipeline-demo"项目，为测试用例
 */
public class JenkinsJobs {
    private static Logger logger = LoggerFactory.getLogger(JenkinsJobs.class);

    // 站点维度 项目集合
    public static Map<String, Job> getProjectsBySite(JenkinsServer jenkins) throws IOException {
        Map<String, Job> jobMap = jenkins.getJobs();

        return jobMap;
    }

    // 某项目维度 分支集合
    public static Map<String, Job> getBranchsOfProject(JenkinsServer jenkins, String projectName) throws IOException {
        FolderJob projectFolder = JenkinsUtils.buildFolderJobByProject(projectName);
        Map<String, Job> jobMap = jenkins.getJobs(projectFolder);

        return jobMap;
    }

    public static void printJob(Job job) {
        logger.info("name: " + job.getName() + "   url: " + job.getUrl() + "   class: " + job.get_class() + "   fullName: " + job.getFullName());
    }

    public static void printJobMap(Map<String, Job> jobMap) {
        for (Map.Entry<String, Job> entry : jobMap.entrySet()) {
            Job job = entry.getValue();
            logger.info("name: " + job.getName() + "   url: " + job.getUrl() + "   class: " + job.get_class() + "   fullName: " + job.getFullName());
        }
    }

    public static void main(String[] args) throws Exception {
        JenkinsServer jenkinsServer = SgJenkinsServer.getInstance();

        JenkinsJobs.getProjectsBySite(jenkinsServer);
        JenkinsJobs.getBranchsOfProject(jenkinsServer, "normal-demo");
        JenkinsJobs.getBranchsOfProject(jenkinsServer, "pipeline-demo");
    }
}
