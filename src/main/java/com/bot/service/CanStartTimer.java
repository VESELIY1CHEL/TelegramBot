package com.bot.service;

public class CanStartTimer {
    private long lastVoteTimeMillis = 0L;
    private static final long VOTE_PERIOD = 90000L;

    public void enableVotes() {
        lastVoteTimeMillis = System.currentTimeMillis();
    }

    public boolean votesEnabled() {
        if(lastVoteTimeMillis==0L) return true;
        else {
            return System.currentTimeMillis() - lastVoteTimeMillis > VOTE_PERIOD;
        }
    }
}
