package com.bot.service;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface SendBotMessageService
{
    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(String chatId,String message);
    void sendMessageKeyBoard(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);
    void deleteMessage(String chatId,Integer messageId);
    void answerToButton(AnswerCallbackQuery answerCallbackQuery);
}
