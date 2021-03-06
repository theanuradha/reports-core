/*******************************************************************************
 * Copyright 2013 CRESOFT AG
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Contributors: CRESOFT AG - initial API and implementation
 ******************************************************************************/
package org.entirej.framework.report.properties.reader;

import org.entirej.framework.report.data.controllers.EJApplicationLevelParameter;
import org.entirej.framework.report.properties.EJCoreReportRuntimeProperties;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class EntireJReportPropertiesHandler extends EJCoreReportPropertiesTagHandler
{
    private EJCoreReportRuntimeProperties _properties;

    private static final String           VERSION                       = "version";
    private static final String           FRAMEWORK                     = "entirejFramework";

    private static final String           CONNECTION_FACTORY_CLASS_NAME = "connectionFactoryClassName";
    private static final String           TRANSLATOR_CLASS_NAME         = "translatorClassName";
    private static final String           REPORTRUNNER_CLASS_NAME       = "reportRunnerClassName";
    private static final String           APPLICATION_LEVEL_PARAMETER   = "appicationLevelParameter";
    private static final String           REPORT_PACKAGE                = "reportPackage";

    private static final String           VISUAL_ATTRIBUTE              = "visualAttribute";

    public EntireJReportPropertiesHandler(EJCoreReportRuntimeProperties properties)
    {

        _properties = properties;

        _properties.getAllRuntimeLevelParameters().clear();
    }

    public EJCoreReportRuntimeProperties getProperties()
    {
        return _properties;
    }

    @Override
    public void startLocalElement(String name, Attributes attributes) throws SAXException
    {

        if (name.equals(APPLICATION_LEVEL_PARAMETER))
        {
            String paramName = attributes.getValue("name");
            String dataTypeName = attributes.getValue("dataType");
            String defaultValue = attributes.getValue("defaultValue");

            EJApplicationLevelParameter parameter = new EJApplicationLevelParameter(paramName, dataTypeName);
            if (defaultValue != null && !defaultValue.isEmpty())
                parameter.setValue(parameter.toDefaultValue(defaultValue));
            _properties.addRuntimeLevelParameter(parameter);
        }
        else if (name.equals(REPORT_PACKAGE))
        {
            _properties.addReportPackageName(attributes.getValue("name"));
        }

        else if (name.equals(VISUAL_ATTRIBUTE))
        {
            setDelegate(new ReportVisualAttributeHandler());
        }

    }

    @Override
    public void endLocalElement(String name, String value, String untrimmedValue) throws SAXException
    {

        if (name.endsWith(FRAMEWORK))
        {
            // ignore
        }
        else if (name.equals(VERSION))
        {
            _properties.setVersion(value);
        }

        else if (name.equals(CONNECTION_FACTORY_CLASS_NAME))
        {
            _properties.setConnectionFactoryClassName(value);
        }
        else if (name.equals(TRANSLATOR_CLASS_NAME))
        {
            _properties.setTranslatorClassName(value);
        }
        else if (name.equals(REPORTRUNNER_CLASS_NAME))
        {
            _properties.setReportRunnerClassName(value);
        }

        else if (name.equals(REPORT_PACKAGE))
        {
            if (value != null && value.trim().length() > 0)
            {
                _properties.getReportPackageNames().add(value);
            }
        }

    }

    @Override
    public void cleanUpAfterDelegate(String name, EJCoreReportPropertiesTagHandler currentDelegate)
    {
        if (name.equals(VISUAL_ATTRIBUTE))
        {
            _properties.getVisualAttributesContainer().addVisualAttribute(((ReportVisualAttributeHandler) currentDelegate).getProperties());
        }

    }

}
