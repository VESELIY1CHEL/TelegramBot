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

import static com.bot.command.CommandName.*;

@Component
public class CrocodileBot extends TelegramLongPollingBot{
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private String nameUser="";

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

            if(nameUser.equals("")||nameUser.equals(update.getCallbackQuery().getFrom().getId().toString())) {
                String commandName = update.getCallbackQuery().getData();
                nameUser = update.getCallbackQuery().getFrom().getId().toString();
                commandContainer.retriveCommand(commandName).execute(update);
            }
            else{
                commandContainer.retriveCommand(UNKNOWN_PERSON.getCommandName()).execute(update);
            }

        }
    }


}
