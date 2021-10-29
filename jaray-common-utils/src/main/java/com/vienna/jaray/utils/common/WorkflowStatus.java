package com.vienna.jaray.utils.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Jaray
 * @date 2020年09月10日 22:29
 * @description: 工作流程状态配置常量
 */
public enum WorkflowStatus {

    /**
     * 工作流程状态码：草稿
     */
    WORKFLOW_DRAFT("D","草稿"),
    /**
     * 工作流程状态码：新建
     */
    WORKFLOW_NEW("N","新建"),
    /**
     * 工作流程状态码：审批中
     */
    WORKFLOW_RUNNING("R","审批中"),
    /**
     * 工作流程状态码：完成
     */
    WORKFLOW_FINISH("F","审批完成");

    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     *  私有化构造方法
     * @param statusCode 状态码
     * @param statusDesc 状态描述
     */
    private WorkflowStatus(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    /**
     * 获取状态描述方法
     * @param statusCode 状态码
     * @return 状态描述
     */
    public static String getStatusDesc(String statusCode) {
        for (WorkflowStatus workflowStatus : WorkflowStatus.values()) {
            if (StringUtils.equalsIgnoreCase(workflowStatus.getStatusCode(), statusCode)) {
                return workflowStatus.statusDesc;
            }
        }
        return null;
    }

    /**
     * 获取状态描述方法
     * @return 状态描述
     */
    public String getStatusDesc() {
        return statusDesc;
    }
    /**
     * 设置状态描述方法
     * @param statusDesc 状态描述
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
    /**
     * 获取状态码方法
     * @return 状态码
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * 设置状态码方法
     * @param statusCode 状态码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
