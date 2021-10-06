package com.bot.command;

import com.bot.service.AnswerCallbackButton;
import com.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class StartGameCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    public final static String START_GAME_MESSAGE="Кто-то взял слово.";

    public StartGameCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService= sendBotMessageService;

    }

    public InlineKeyboardMarkup startMessage(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button11 = new InlineKeyboardButton();
        InlineKeyboardButton button12 = new InlineKeyboardButton();
        InlineKeyboardButton button21 = new InlineKeyboardButton();
        InlineKeyboardButton button22 = new InlineKeyboardButton();
        InlineKeyboardButton button23 = new InlineKeyboardButton();
        List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardRow2 = new ArrayList<>();
        button11.setText("Посмотреть");
        button11.setCallbackData("start_game");
        button12.setText("Случайное");
        button12.setCallbackData("randomWord");

        keyboardRow1.add(button11);
        keyboardRow1.add(button12);

        button21.setText("Лёгкое");
        button21.setCallbackData("easyWord");
        button22.setText("Обычное");
        button22.setCallbackData("normalWord");
        button23.setText("Сложное");
        button23.setCallbackData("hardWord");
        keyboardRow2.add(button21);
        keyboardRow2.add(button22);
        keyboardRow2.add(button23);



        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow1);
        keyboard.add(keyboardRow2);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
    @Override
    public void execute(Update update) {

        sendBotMessageService.answerToButton(AnswerCallbackButton.showWord(update.getCallbackQuery().getId()));
        sendBotMessageService.deleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(),update.getCallbackQuery().getMessage().getMessageId());
        sendBotMessageService.sendMessageKeyBoard(update.getCallbackQuery().getMessage().getChatId().toString(),START_GAME_MESSAGE,startMessage());
    }
}
