package com.bot.command;

import com.bot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.bot.command.CommandName.*;
import static java.util.stream.LongStream.builder;

public class CommandContainer {

    private final Map<String,Command> commandMap;
    private final Command unknownCommand;
    private String word;


    public CommandContainer(SendBotMessageService sendBotMessageService,String word){

        this.word = word;
        commandMap = new TreeMap<>();
                commandMap.put(START.getCommandName(),new StartCommand(sendBotMessageService));
                commandMap.put(STOP.getCommandName(),new StopCommand(sendBotMessageService));
                commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
                commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
                commandMap.put(START_GAME.getCommandName(),new StartGameCommand(sendBotMessageService,this.word));
                commandMap.put(SHOW_WORD.getCommandName(),new ShowCommand(sendBotMessageService,this.word));
                commandMap.put(UNKNOWN_PERSON.getCommandName(),new UnknownPersonCommand(sendBotMessageService));
                commandMap.put(CHANGE_WORD.getCommandName(),new ChangeCommand(sendBotMessageService, this.word));

        unknownCommand = new UnknownCommand(sendBotMessageService);

    }

    public Command retriveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier,unknownCommand);
    }
    public void setWord(String word,SendBotMessageService sendBotMessageService){
        this.word= word;
        commandMap.put(CHANGE_WORD.getCommandName(),new ChangeCommand(sendBotMessageService,word));
        commandMap.put(START_GAME.getCommandName(),new StartGameCommand(sendBotMessageService,word));
        commandMap.put(SHOW_WORD.getCommandName(),new ShowCommand(sendBotMessageService, word));
    }

}
