package com.bot.command;

import com.bot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.bot.command.CommandName.*;
import static java.util.stream.LongStream.builder;

public class CommandContainer {

    private final ImmutableMap<String,Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService){

        commandMap = ImmutableMap.<String,Command>builder()
                .put(START.getCommandName(),new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(),new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(START_GAME.getCommandName(),new StartGameCommand(sendBotMessageService))
                .put(SHOW_WORD.getCommandName(),new ShowCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);

    }

    public Command retriveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier,unknownCommand);
    }
}
