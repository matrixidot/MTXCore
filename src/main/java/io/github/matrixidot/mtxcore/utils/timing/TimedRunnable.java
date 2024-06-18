package io.github.matrixidot.mtxcore.utils.timing;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A {@link org.bukkit.scheduler.BukkitRunnable} extension that will cancel itself after a specified number of ticks.
 */
public abstract class TimedRunnable extends BukkitRunnable {
    private final AtomicLong ticksLeft;
    private final long period;

    /**
     * Constructs the TimedRunnable
     * @param ticks The number of ticks you want this runnable to last for
     * @param period Should be equal to the period when you call {@link org.bukkit.scheduler.BukkitRunnable}{@link #runTaskTimer(Plugin, long, long)}, decrements ticks properly.
     */
    public TimedRunnable(long ticks, long period) {
        ticksLeft = new AtomicLong(ticks);
        this.period = period;
    }

    @Override
    public void run() {
        checkDuration();
        update();
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        cancelled();
    }

    public abstract void update();


    private void checkDuration() {
        if (isCancelled()) return;
        if (ticksLeft.get() <= 0) {
            cancel();
            return;
        }
        else
            ticksLeft.set(ticksLeft.get() - period);
    }

    public void cancelled() {
        super.cancel();
    }
    public int getSecondsLeft() {
        return ticksLeft.intValue() / 20;
    }
    public long getTicks() {
        return ticksLeft.get();
    }
}
