package com.bot.command;
/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    NO("/no"),
    HELP("/help"),
    START_GAME("start_game"),
    UNKNOWN_PERSON("unknown_person"),
    SHOW_WORD("show_word"),
    CHANGE_WORD("random_word"),
    GUESS_WORD("guess_word");


    private final String commandName;

    CommandName(String commandName){
        this.commandName=commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
