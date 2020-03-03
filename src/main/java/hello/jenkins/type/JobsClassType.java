package hello.jenkins.type;

/**
 * @创建人 sgwang
 * @name JobsClassType
 * @user Anti
 * @创建时间 2020/3/3
 * @描述 枚举Jenkins中，jobs中常见class
 */
public enum JobsClassType {
    FreeStyle("hudson.model.FreeStyleProject", 1),
    WorkflowMultiBranch("org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject", 7);

    private String classNameStr;
    private Integer index;

    private JobsClassType(String classNameStr, int index) {
        this.classNameStr = classNameStr;
        this.index = index;
    }

    /**
     * 根据类的的全名称，返回枚举实例。
     *
     * @param classNameStr
     * @return JobsClassType | null
     */
    public static JobsClassType getByClassNameStr(String classNameStr) {
        for (JobsClassType temp : JobsClassType.values()) {
            if (temp.getClassNameStr().equals(classNameStr)) {
                return temp;
            }
        }

        return null;
    }

    public String getClassNameStr() {
        return classNameStr;
    }

    public void setClassNameStr(String classNameStr) {
        this.classNameStr = classNameStr;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
