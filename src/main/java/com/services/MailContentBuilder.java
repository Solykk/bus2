package com.services;

import com.domain.request.BookTicketsInternalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(BookTicketsInternalRequest request) {
        Context context = new Context();
        context.setVariable("startCity", request.getStartCity());
        context.setVariable("endCity", request.getEndCity());
        context.setVariable("startDate", request.getStartDate());
        context.setVariable("returnDate", request.getReturnDate() != null? request.getReturnDate() : "One way");
        context.setVariable("passengers", request.getPassengers());
        context.setVariable("name", request.getName());
        context.setVariable("surname", request.getSurname());
        context.setVariable("email", request.getEmail());
        context.setVariable("phone", request.getPhone());
        context.setVariable("message", request.getMessage());
        context.setVariable("bookingId", request.getId());
        context.setVariable("bookingDate", request.getCreateDate());
        context.setVariable("rate", request.getRate() + " грн");
        context.setVariable("price", request.getPrice() + " грн");
        return templateEngine.process("bookTicketsMailTemplate", context);
    }

}