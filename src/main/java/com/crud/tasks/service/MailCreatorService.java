package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetails companyDetails;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview", "New card has been added on your Trello account");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://pmerecki.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("company_details", companyDetails.getCompanyName() + "\n" +
                companyDetails.getCompanyGoal() + " " + companyDetails.getCompanyPhone());
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildNumberOfTasksEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context2 = new Context();
        context2.setVariable("preview", "Number of existing tasks in database");
        context2.setVariable("message", message);
        context2.setVariable("tasks_url", "https://pmerecki.github.io/");
        context2.setVariable("button", "Visit website");
        context2.setVariable("company_details", companyDetails.getCompanyName() + "\n" +
                companyDetails.getCompanyGoal() + " " + companyDetails.getCompanyPhone());
        context2.setVariable("goodbye_message", "Best regards");
        context2.setVariable("show_button", true);
        context2.setVariable("is_friend", false);
        context2.setVariable("admin_config", adminConfig);
        context2.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/number-of-tasks-mail", context2);
    }
}

