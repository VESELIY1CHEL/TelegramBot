package com.bot.service;

import com.bot.telegramBot.CrocodileBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
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
    public void sendMessageKeyBoard(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try{
            crocodileBot.execute(sendMessage);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void deleteMessage(String chatId,Integer messageId){
        try{
            crocodileBot.execute( DeleteMessage.builder().chatId(chatId).messageId(messageId).build());
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void answerToButton(AnswerCallbackQuery answerCallbackQuery) {
        try{
            crocodileBot.execute( answerCallbackQuery);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
}
