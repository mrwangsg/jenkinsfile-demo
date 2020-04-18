package hello.jenkins.job;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.QueueReference;
import hello.utils.SgJenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @创建人 sgwang
 * @name JenkinsJobOper
 * @user Anti
 * @创建时间 2020/3/3
 * @描述
 */
public class JenkinsJobOper {
    public static Logger logger = LoggerFactory.getLogger(JenkinsJobOper.class);

    /**
     * 禁用 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @throws IOException
     */
    public static void disableJob(JenkinsServer jenkinsServer, String jobName) throws IOException {
        jenkinsServer.disableJob(jobName, true);
    }

    /**
     * 禁用 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @param crumbFlag     貌似与 “防止跨站点请求伪造” 安全有关。使用false，应该还需要其他的配置。测试环境推荐使用true.
     * @throws IOException
     */
    public static void disableJob(JenkinsServer jenkinsServer, String jobName, boolean crumbFlag) throws IOException {
        jenkinsServer.disableJob(jobName, crumbFlag);
    }

    /**
     * 启动 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @throws IOException
     */
    public static void enableJob(JenkinsServer jenkinsServer, String jobName) throws IOException {
        jenkinsServer.enableJob(jobName, true);
    }

    /**
     * 启动 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @param crumbFlag     貌似与 “防止跨站点请求伪造” 安全有关。使用false，应该还需要其他的配置。测试环境推荐使用true.
     * @throws IOException
     */
    public static void enableJob(JenkinsServer jenkinsServer, String jobName, boolean crumbFlag) throws IOException {
        jenkinsServer.enableJob(jobName, crumbFlag);
    }

    /**
     * 主动发起构建job
     *
     * @param job 包含项目级别job和分支级别job
     * @return QueueReference
     * @throws IOException
     */
    public static QueueReference buildJob(Job job) throws IOException {
        QueueReference build = job.build();
        return build;
    }

    /**
     * 删除 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @throws IOException || HttpResponseException
     */
    public static void deleteJob(JenkinsServer jenkinsServer, String jobName) throws IOException {
        jenkinsServer.deleteJob(jobName);
    }

    /**
     * 删除 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @param crumbFlag     貌似与 “防止跨站点请求伪造” 安全有关。使用false，应该还需要其他的配置。测试环境推荐使用true.
     * @throws IOException || HttpResponseException
     */
    public static void deleteJob(JenkinsServer jenkinsServer, String jobName, boolean crumbFlag) throws IOException {
        jenkinsServer.deleteJob(jobName, crumbFlag);
    }

    /**
     * 创建 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @param jobXmlData    config.xml配置文件 String形式
     * @throws IOException
     */
    public static void createJob(JenkinsServer jenkinsServer, String jobName, String jobXmlData) throws IOException {
        jenkinsServer.createJob(jobName, jobXmlData, true);
    }

    /**
     * 创建 项目级别job
     *
     * @param jenkinsServer
     * @param jobName       其实是项目名的大致意思
     * @param jobXmlData    config.xml配置文件 String形式
     * @param crumbFlag     貌似与 “防止跨站点请求伪造” 安全有关。使用false，应该还需要其他的配置。测试环境推荐使用true.
     * @throws IOException
     */
    public static void createJob(JenkinsServer jenkinsServer, String jobName, String jobXmlData, boolean crumbFlag) throws IOException {
        jenkinsServer.createJob(jobName, jobXmlData, crumbFlag);
    }


    public static void main(String[] args) throws Exception {
        JenkinsServer jenkinsServer = SgJenkinsServer.getInstance();

        logger.info("------------------------ 删除job 分割线-----------------------");
        JenkinsJobOper.deleteJob(jenkinsServer, "pipeline-demo-02");


        logger.info("------------------------ 创建job 分割线-----------------------");
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File xmlFile = new File(classpath + "pipeline-config.xml");
        System.out.println("xmlFile: " + xmlFile.getPath());

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile)));
        String line = null;
        StringBuffer strBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuffer.append(line);
        }
//        JenkinsJobOper.createJob(jenkinsServer, "pipeline-demo-02", strBuffer.toString());


//        logger.info("------------------------ 禁用/启动job 分割线-----------------------");
//        Map<String, Job> siteMap = jenkinsServer.getJobs();
//        for (Map.Entry<String, Job> siteJob : siteMap.entrySet()) {
//            JenkinsJobOper.disableJob(jenkinsServer, siteJob.getKey(), false);
//            JenkinsJobOper.enableJob(jenkinsServer, siteJob.getKey(), false);
//        }

//        logger.error("--------------------------- 构建job 分割线 -------------------------------");
//        Map<String, Job> projectJobsMap = new HashMap<>();
//        Map<String, Map<String, Job>> branchJobsMap = new HashMap<>();
//
//        Map<String, Job> siteMap = JenkinsJobs.getProjectsBySite(jenkinsServer);
//        for (Map.Entry<String, Job> siteJob : siteMap.entrySet()) {
//            String tempProjectName = siteJob.getKey();
//            Job tempJob = siteJob.getValue();
//
//            if (JenkinsUtils.ifExistBranch(tempJob.get_class())) {
//                Map<String, Job> tempJobMap = JenkinsJobs.getBranchsOfProject(jenkinsServer, siteJob.getKey());
//                branchJobsMap.put(tempProjectName, tempJobMap);
//            } else {
//                projectJobsMap.put(tempProjectName, tempJob);
//            }
//        }
//
//        for (Map.Entry<String, Job> single : projectJobsMap.entrySet()) {
//            Job singleJob = single.getValue();
//            JenkinsJobOper.buildJob(singleJob);
//        }
//
//        for (Map.Entry<String, Map<String, Job>> outer : branchJobsMap.entrySet()) {
//            Map<String, Job> outMap = outer.getValue();
//            for (Map.Entry<String, Job> inner : outMap.entrySet()) {
//                Job innerJob = inner.getValue();
//                JenkinsJobOper.buildJob(innerJob);
//            }
//        }
    }

}