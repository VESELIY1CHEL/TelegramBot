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

    private String word;

    public StartGameCommand(SendBotMessageService sendBotMessageService,String word){
        this.sendBotMessageService= sendBotMessageService;
        this.word = word;

    }

    public InlineKeyboardMarkup startMessage(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button11 = new InlineKeyboardButton();
        InlineKeyboardButton button12 = new InlineKeyboardButton();
        List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
        button11.setText("Посмотреть");
        button11.setCallbackData("show_word");
        button12.setText("Случайное");
        button12.setCallbackData("random_word");
        keyboardRow1.add(button11);
        keyboardRow1.add(button12);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardRow1);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
    @Override
    public void execute(Update update) {

        String stMessage = "\uD83D\uDC0AИтак <a href=\"tg://user?id="+update.getCallbackQuery().getFrom().getId().toString()+"\">"+update.getCallbackQuery().getFrom().getFirstName()+"</a>"+" должен объяснить слово за 2 минуты";
        sendBotMessageService.answerToButton(AnswerCallbackButton.showWord(update.getCallbackQuery().getId(),word));
        sendBotMessageService.deleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(),update.getCallbackQuery().getMessage().getMessageId());
        sendBotMessageService.sendMessageKeyBoard(update.getCallbackQuery().getMessage().getChatId().toString(),stMessage,startMessage());
    }
}
