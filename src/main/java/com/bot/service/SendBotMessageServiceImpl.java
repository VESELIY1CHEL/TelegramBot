package com.bot.service;

import com.bot.telegramBot.CrocodileBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final CrocodileBot crocodileBot;

    @Autowired
    public SendBotMessageServiceImpl(CrocodileBot crocodileBot){
        this.crocodileBot= crocodileBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        try{
            crocodileBot.execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
