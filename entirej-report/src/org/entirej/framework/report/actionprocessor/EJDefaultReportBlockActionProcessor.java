/*******************************************************************************
 * Copyright 2013 CRESOFT AG
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
 *     CRESOFT AG - initial API and implementation
 ******************************************************************************/
package org.entirej.framework.report.actionprocessor;

import org.entirej.framework.report.EJReport;
import org.entirej.framework.report.EJReportActionProcessorException;
import org.entirej.framework.report.EJReportRecord;
import org.entirej.framework.report.actionprocessor.interfaces.EJReportBlockActionProcessor;
import org.entirej.framework.report.enumerations.EJReportTableColumn;
import org.entirej.framework.report.enumerations.EJReportScreenSection;
import org.entirej.framework.report.service.EJReportQueryCriteria;

public class EJDefaultReportBlockActionProcessor implements EJReportBlockActionProcessor
{
    public void postQuery(EJReport report, EJReportRecord record) throws EJReportActionProcessorException
    {
    }

    public void preBlockQuery(EJReport report, EJReportQueryCriteria queryCriteria) throws EJReportActionProcessorException
    {
    }

    @Override
    public boolean canShowBlock(EJReport report, String blockName)
    {
        return true;
    }

    @Override
    public boolean canShowScreenItem(EJReport report, String blockName, String screenItem, EJReportScreenSection section)
    {
        return true;
    }

    @Override
    public boolean canShowScreenColumn(EJReport report, String blockName, String columnName)
    {
        return true;
    }

    @Override
    public boolean canShowBlockFooter(EJReport report, String blockName)
    {
        return true;
    }

    @Override
    public boolean canShowBlockHeader(EJReport report, String blockName)
    {
        return true;
    }

    @Override
    public boolean canShowScreenColumnSection(EJReport report, String blockName, String columnName, EJReportScreenSection section)
    {
        return true;
    }
    
    @Override
    public EJReportTableColumn getHiddenColumnLayout(EJReport report, String blockName, String columnName)
    {
        return EJReportTableColumn.MOVE;
    }
}
