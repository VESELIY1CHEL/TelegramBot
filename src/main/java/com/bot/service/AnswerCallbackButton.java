package com.bot.service;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

public class AnswerCallbackButton {
    public static AnswerCallbackQuery showWord(String id,String word){
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setShowAlert(true);
        answerCallbackQuery.setText(word);
        answerCallbackQuery.setCallbackQueryId(id);
        return answerCallbackQuery;
    }
}
