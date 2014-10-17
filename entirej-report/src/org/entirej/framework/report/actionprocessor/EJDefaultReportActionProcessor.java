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
package org.entirej.framework.report.actionprocessor;

import org.entirej.framework.report.EJReport;
import org.entirej.framework.report.EJReportActionProcessorException;
import org.entirej.framework.report.EJReportBlock;
import org.entirej.framework.report.EJReportRecord;
import org.entirej.framework.report.actionprocessor.interfaces.EJReportActionProcessor;
import org.entirej.framework.report.service.EJReportQueryCriteria;

public class EJDefaultReportActionProcessor implements EJReportActionProcessor
{

    public void preReport(EJReport report, String blockName) throws EJReportActionProcessorException
    {
    }

    public void newReportInstance(EJReport report) throws EJReportActionProcessorException
    {
    }

    public void postQuery(EJReport report, EJReportRecord record) throws EJReportActionProcessorException
    {
    }

    public void postBlockQuery(EJReport report, EJReportBlock block) throws EJReportActionProcessorException
    {
    }

    public void newRecordInstance(EJReport report, EJReportRecord record) throws EJReportActionProcessorException
    {
    }

    public void validateQueryCriteria(EJReport report, EJReportQueryCriteria queryCriteria) throws EJReportActionProcessorException
    {
    }

    public void preUpdate(EJReport report, EJReportRecord record) throws EJReportActionProcessorException
    {
    }

    public void preQuery(EJReport report, EJReportQueryCriteria queryCriteria) throws EJReportActionProcessorException
    {
    }

    public void initialiseRecord(EJReport report, EJReportRecord record) throws EJReportActionProcessorException
    {
    }

}