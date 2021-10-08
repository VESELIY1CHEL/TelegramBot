package com.bot.command;

import com.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "Игра ещё идёт";
    public NoCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),NO_MESSAGE);
        sendBotMessageService.deleteMessage(update.getMessage().getChatId().toString(),update.getMessage().getMessageId());
    }
}
