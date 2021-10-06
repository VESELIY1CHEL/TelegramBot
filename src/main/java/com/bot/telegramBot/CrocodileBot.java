package com.bot.telegramBot;

import com.bot.command.Command;
import com.bot.command.CommandContainer;
import com.bot.service.AnswerCallbackButton;
import com.bot.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.bot.command.CommandName.NO;
import static com.bot.command.CommandName.START_GAME;

@Component
public class CrocodileBot extends TelegramLongPollingBot{
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public CrocodileBot(){
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();

            if(update.getMessage().getChat().isGroupChat()) {
                if (message.startsWith(COMMAND_PREFIX)) {

                    String commandIdentifier = message.split(" ")[0].toLowerCase();

                    commandContainer.retriveCommand(commandIdentifier).execute(update);
                } else {
                    commandContainer.retriveCommand(NO.getCommandName()).execute(update);
                }
            }
            else commandContainer.retriveCommand(NO.getCommandName()).execute(update);
        }
        else if(update.hasCallbackQuery()){

                if(update.getCallbackQuery().getData().equals(START_GAME.getCommandName())) {
                    commandContainer.retriveCommand(START_GAME.getCommandName()).execute(update);
                }
        }
    }


}
