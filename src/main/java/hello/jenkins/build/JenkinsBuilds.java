package hello.jenkins.build;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import hello.utils.SgJenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @创建人 sgwang
 * @name JenkinsBuilds
 * @user Anti
 * @创建时间 2020/3/2
 * @描述
 */
public class JenkinsBuilds {
    private static Logger logger = LoggerFactory.getLogger(JenkinsBuilds.class);

    // 获取构建历史记录
    public static List<Build> getBuildsByJob(Job job) {
        JobWithDetails jobWithDetails = (JobWithDetails) job;

        List<Build> buildList = jobWithDetails.getBuilds(); // 最多100个，想要更多 使用getAllBuilds()

        return buildList;
    }


    public static void printBuild(Build build) {
        logger.info("number: " + build.getNumber() + "   queueId: " + build.getQueueId() + "   url: " + build.getUrl() + "   class: " + build.get_class());
    }

    public static void printBuildList(List<Build> buildList) {
        for (Build build : buildList) {
            logger.info("number: " + build.getNumber() + "   queueId: " + build.getQueueId() + "   url: " + build.getUrl() + "   class: " + build.get_class());
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
