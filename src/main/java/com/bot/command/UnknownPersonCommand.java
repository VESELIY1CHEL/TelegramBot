package com.bot.command;

import com.bot.service.AnswerCallbackButton;
import com.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownPersonCommand implements Command {
    static final String UNKNOWN_PERSON="Не вы загадываете слово";

    private SendBotMessageService sendBotMessageService;

    public UnknownPersonCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService=sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.answerToButton(AnswerCallbackButton.showWord(update.getCallbackQuery().getId(),UNKNOWN_PERSON));
    }
}
