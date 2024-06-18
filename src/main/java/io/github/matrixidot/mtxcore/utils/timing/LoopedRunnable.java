package io.github.matrixidot.mtxcore.utils.timing;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class LoopedRunnable extends BukkitRunnable {
    private final AtomicInteger loopsLeft;

    /**
     * Constructs the LoopedRunnable
     * @param loops The number of times this runnable should loop
     */
    public LoopedRunnable(int loops) {
        loopsLeft = new AtomicInteger(loops);
    }

    @Override
    public void run() {
        checkLoops();
        update();
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        cancelled();
    }

    public abstract void update();

    private void checkLoops() {
        if (isCancelled()) return;
        if (loopsLeft.get() <= 0)
            cancel();
        else
            loopsLeft.set(loopsLeft.get() - 1);
    }
    public void cancelled() {
        cancel();
    }
    public int getLoopsLeft() {
        return loopsLeft.get();
    }

}
