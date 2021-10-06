package com.bot.command;

import com.bot.service.AnswerCallbackButton;
import com.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ShowCommand implements Command {
    SendBotMessageService sendBotMessageService;
    public ShowCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public void execute(Update update) {
        sendBotMessageService.answerToButton(AnswerCallbackButton.showWord(update.getCallbackQuery().getId(),"push on show button"));
    }
}
