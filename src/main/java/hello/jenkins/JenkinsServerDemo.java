package hello.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import hello.jenkins.job.JenkinsJobs;
import hello.utils.JenkinsUtils;
import hello.utils.SgJenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name JenkinsServerDemo
 * @user Anti
 * @创建时间 2020/3/2
 * @描述 JenkinsServer对象创建后，会内置一些Jenkins上的全局配置信息，所以需要 careful！
 */
public class JenkinsServerDemo {

    private static Logger logger = LoggerFactory.getLogger(JenkinsServerDemo.class);

    public static void main(String[] args) throws Exception {
        JenkinsServer jenkinsServer = SgJenkinsServer.getInstance();
        Map<String, Job> projectJobsMap = new HashMap<>();
        Map<String, Map<String, Job>> branchJobsMap = new HashMap<>();

        Map<String, Job> siteMap = JenkinsJobs.getProjectsBySite(jenkinsServer);
        for (Map.Entry<String, Job> siteJob : siteMap.entrySet()) {
            String tempProjectName = siteJob.getKey();
            Job tempJob = siteJob.getValue();

            if (JenkinsUtils.ifExistBranch(tempJob.get_class())) {
                Map<String, Job> tempJobMap = JenkinsJobs.getBranchsOfProject(jenkinsServer, siteJob.getKey());
                branchJobsMap.put(tempProjectName, tempJobMap);
            } else {
                projectJobsMap.put(tempProjectName, tempJob);
            }

        }

        // 打印缓存
        logger.error("--------------------------------- 分割线01 -------------------------------");
        Map<String, List<Build>> buildsMap = new HashMap<>();

        for (Map.Entry<String, Job> single : projectJobsMap.entrySet()) {
            logger.error("singleStr: " + single.getKey() + "   singleJob: " + single.getValue().getName());

            JobWithDetails jobWithDetails = single.getValue().details();
            buildsMap.put(single.getKey(), jobWithDetails.getBuilds());
        }

        for (Map.Entry<String, Map<String, Job>> outer : branchJobsMap.entrySet()) {
            String outerStr = outer.getKey();
            Map<String, Job> outMap = outer.getValue();
            logger.error("outerStr: " + outerStr);

            for (Map.Entry<String, Job> inner : outMap.entrySet()) {
                String innerStr = inner.getKey();
                Job innerJob = inner.getValue();
                logger.error("      innerStr: " + innerStr + "   innerJob: " + innerJob.getName());

                JobWithDetails jobWithDetails = inner.getValue().details();
                buildsMap.put(inner.getKey(), jobWithDetails.getBuilds());
            }
        }

        logger.error("--------------------------------- 分割线02 -------------------------------");
        for (Map.Entry<String, List<Build>> entry : buildsMap.entrySet()) {
            String keyStr = entry.getKey();
            List<Build> buildList = entry.getValue();

            for (Build build : buildList) {
                logger.error("keyStr: " + keyStr + "   number: " + build.getNumber() + "   queueId: " + build.getQueueId() + "   url: " + build.getUrl());
                // 不要轻易调用 目前并没发现这两个方法有什么用
                // logger.error("testReport: " + build.getTestReport() + "   testResult: " + build.getTestResult());
            }
        }

    }
}
