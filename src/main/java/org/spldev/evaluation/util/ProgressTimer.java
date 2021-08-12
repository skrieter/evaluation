/* -----------------------------------------------------------------------------
 * Evaluation Lib - Miscellaneous functions for performing an evaluation.
 * Copyright (C) 2021  Sebastian Krieter
 * 
 * This file is part of Evaluation Lib.
 * 
 * Evaluation Lib is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * Evaluation Lib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Evaluation Lib.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * See <https://github.com/skrieter/evaluation> for further information.
 * -----------------------------------------------------------------------------
 */
package org.spldev.evaluation.util;

import org.spldev.util.logging.*;

/**
 * @author Sebastian Krieter
 */
public class ProgressTimer {

	private boolean running = false;
	private boolean verbose = true;

	private long startTime;

	private long curTime = 0;

	private long lastTime = -1;

	private static long getTime() {
		return System.nanoTime();
	}

	public void start() {
		if (!running) {
			startTime = getTime();
			curTime = startTime;
			running = true;
		}
	}

	public long stop() {
		if (running) {
			lastTime = getTime() - startTime;

			printTime();

			running = false;
		}
		return lastTime;
	}

	public long split() {
		final long startTime = curTime;
		curTime = getTime();

		lastTime = curTime - startTime;

		printTime();

		return lastTime;
	}

	private void printTime() {
		if (verbose) {
			final double timeDiff = (lastTime / 1_0000_00L) / 1_000.0;
			Logger.logInfo("Time: " + timeDiff + "s");
		}
	}

	public final boolean isRunning() {
		return running;
	}

	public long getLastTime() {
		return lastTime;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

}
