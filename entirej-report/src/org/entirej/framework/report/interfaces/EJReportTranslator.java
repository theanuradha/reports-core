/*******************************************************************************
 * Copyright 2013 Mojave Innovations GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Mojave Innovations GmbH - initial API and implementation
 ******************************************************************************/
package org.entirej.framework.report.interfaces;

import java.io.Serializable;
import java.util.Locale;

import org.entirej.framework.report.EJReportMessage;
import org.entirej.framework.report.EJReportTranslatorHelper;

public interface EJReportTranslator extends Serializable
{
    /**
     * Translates the given <code>textCode</code>
     * <p>
     * The <code>textCode</code> is the applications key for the text to be
     * translated
     * 
     * @param helper
     *            Contains methods that can assist in the translation of texts
     *            and messages, for example, the {@link Locale} that is
     *            currently set for the application
     * @param textCode
     *            The code of the text to be translated
     * @return The translated text
     */
    public String translateText(EJReportTranslatorHelper helper, String textCode);

    /**
     * Translates a given message text. Message texts are either used within the
     * core framework or used within the <code>{@link EJReportMessage}</code>
     * <p>
     * This The <code>messageTextCode</code> is the applications key for the
     * text to be translated.
     * 
     * @param helper
     *            Contains methods that can assist in the translation of texts
     *            and messages, for example, the {@link Locale} that is
     *            currently set for the application
     * @param messageTextCode
     *            The code of the text to be translated
     * @return The translated text
     */
    public String translateMessageText(EJReportTranslatorHelper helper, String messageTextCode);
}
