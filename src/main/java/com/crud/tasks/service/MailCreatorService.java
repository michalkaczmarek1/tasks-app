package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.goal}")
    private String companyGoal;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyPhone;

    @Autowired
    private TaskRepository taskRepository;

    private List<Task> tasks = new ArrayList<>();

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://michalkaczmarek1.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Dziekujemy " + adminConfig.getAdminName() + " za skorzystanie z naszego serwisu! Miłego dnia");
        context.setVariable("company_details", companyName + ", " + companyEmail + ", tel." + companyPhone + " | " + companyGoal);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {

        List<Task> tasks = taskRepository.findAll();

        Context context = new Context();

        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://michalkaczmarek1.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", companyName + ", " + companyEmail + ", tel." + companyPhone + " | " + companyGoal);
        context.setVariable("goodbye_message", "Dziekujemy " + adminConfig.getAdminName() + " za skorzystanie z naszego serwisu! Miłego dnia");
        if(taskRepository.count() > 0) {
            context.setVariable("tasks", tasks);
            context.setVariable("amountTasksGreaterThanZero", true);
        } else {
            context.setVariable("amountTasksGreaterThanZero", false);
        }

        return templateEngine.process("mail/scheduled-mail", context);
    }

}
