/*******************************************************************************
 * Copyright (c) 2008, 2011 Sonatype Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.tycho.repository.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.tycho.core.shared.MavenLogger;

/**
 * Special {@link IProgressMonitor} instance which writes the task names it is given via the
 * {@link IProgressMonitor} interface to the log.
 */
public class LoggingProgressMonitor implements IProgressMonitor {

    private final MavenLogger logger;

    public LoggingProgressMonitor(MavenLogger logger) {
        this.logger = logger;
    }

    private void writeToLog(String text) {
        if (text == null || text.length() == 0) {
            return;
        } else if (suppressOutputOf(text)) {
            return;
        }

        logger.info(text);
    }

    /**
     * @param text
     *            The candidate text for logging. Never <code>null</code>.
     */
    protected boolean suppressOutputOf(@SuppressWarnings("unused") String text) {
        // default implementation
        return false;
    }

    @Override
    public final void beginTask(String name, int totalWork) {
        writeToLog(name);
    }

    @Override
    public final void done() {
    }

    @Override
    public final void internalWorked(double work) {
    }

    @Override
    public final boolean isCanceled() {
        return false;
    }

    @Override
    public final void setCanceled(boolean value) {
    }

    @Override
    public final void setTaskName(String name) {
        writeToLog(name);
    }

    @Override
    public final void subTask(String name) {
        writeToLog(name);
    }

    @Override
    public final void worked(int work) {
    }

}
