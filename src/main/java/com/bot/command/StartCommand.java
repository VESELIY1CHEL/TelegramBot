package com.bot.command;

import com.bot.service.SendBotMessageService;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE="Я создан для игры в крокодил в твоей группе";

    public StartCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService= sendBotMessageService;

    }
    @Override
    public void execute(Update update){
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),START_MESSAGE);
    }
}
