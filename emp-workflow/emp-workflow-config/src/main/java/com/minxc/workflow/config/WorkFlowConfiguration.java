package com.minxc.workflow.config;

import org.flowable.engine.*;
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkFlowConfiguration {

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
//        springProcessEngineConfiguration.setDataSource()
        return springProcessEngineConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean() {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngineFactoryBean().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngineFactoryBean().getObject().getTaskService();
    }
    @Bean
    public RepositoryService repositoryService() throws Exception{
        return processEngineFactoryBean().getObject().getRepositoryService();
    }


    @Bean
    public  FormService formService() throws Exception{
        return processEngineFactoryBean().getObject().getFormService();
    }

    @Bean
    public HistoryService historyService() throws Exception{
        return processEngineFactoryBean().getObject().getHistoryService();
    }

    @Bean
    public IdentityService identityService() throws Exception{
        return processEngineFactoryBean().getObject().getIdentityService();
    }

    @Bean
    public ManagementService managementService() throws Exception{
        return processEngineFactoryBean().getObject().getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService() throws Exception{
        return processEngineFactoryBean().getObject().getDynamicBpmnService();
    }

}
