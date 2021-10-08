package com.bot.command;

import com.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GuessWordCommand implements Command {
    SendBotMessageService sendBotMessageService;
    String word;

    public GuessWordCommand(SendBotMessageService sendBotMessageService, String word){
        this.word = word;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String message = "\uD83D\uDC0A<a href=\"tg://user?id="+update.getMessage().getFrom().getId().toString()+"\">"
                +update.getMessage().getFrom().getFirstName()+"</a> отгадал слово <b>"+word+"</b>";

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),message);
    }
}
