package com.bot.command;

import com.bot.service.SendBotMessageService;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE="\uD83D\uDC0AПривет, кто хочет взять слово?";

    public StartCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService= sendBotMessageService;

    }
    public InlineKeyboardMarkup startMessage(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Я хочу!");
        button.setCallbackData("start_game");
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        keyboardRow.add(button);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
    @Override
    public void execute(Update update){
        sendBotMessageService.sendMessageKeyBoard(update.getMessage().getChatId().toString(),START_MESSAGE,startMessage());
    }
}
