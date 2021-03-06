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
package org.entirej.framework.report.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.entirej.framework.report.EJReportFrameworkManager;
import org.entirej.framework.report.EJReportMessageFactory;
import org.entirej.framework.report.EJReportRuntimeException;
import org.entirej.framework.report.data.controllers.EJApplicationLevelParameter;
import org.entirej.framework.report.enumerations.EJReportFrameworkMessage;
import org.entirej.framework.report.interfaces.EJEntireJReportProperties;
import org.entirej.framework.report.interfaces.EJReportConnectionFactory;
import org.entirej.framework.report.interfaces.EJReportRunner;
import org.entirej.framework.report.interfaces.EJReportTranslator;

public class EJCoreReportRuntimeProperties implements EJEntireJReportProperties
{

    private String                               _version               = "1.0";                                           // default

    private ArrayList<String>                    _reportPackageNames;
    private String                               _connectionFactoryClassName;
    private String                               _translatorClassName;
    private String                               _reportRunnerClassName = "org.entirej.report.EJReportDefaultRunner";

    private EJReportVisualAttributeContainer     _visualAttributeContainer;

    private List<EJApplicationLevelParameter>    _runtimeLevelParameters;

    private static EJCoreReportRuntimeProperties _instance;

    private EJReportTranslator                   _applicationTranslator;

    static
    {
        _instance = new EJCoreReportRuntimeProperties();
    }

    public static EJCoreReportRuntimeProperties getInstance()
    {
        return _instance;
    }

    public EJCoreReportRuntimeProperties()
    {
        _reportPackageNames = new ArrayList<String>();
        _visualAttributeContainer = new EJReportVisualAttributeContainer(new ArrayList<EJCoreReportVisualAttributeProperties>());

        _runtimeLevelParameters = new ArrayList<EJApplicationLevelParameter>();
    }

    public String getReportRunnerClassName()
    {
        return _reportRunnerClassName;
    }

    public void setReportRunnerClassName(String reportRunnerClassName)
    {
        _reportRunnerClassName = reportRunnerClassName;

    }

    public EJReportRunner newReportRunner()
    {
        if (_reportRunnerClassName == null || _reportRunnerClassName.trim().length() == 0)
        {
            return null;
        }

        try
        {
            Class<?> rendererClass = Class.forName(_reportRunnerClassName);
            Object obj = rendererClass.newInstance();

            if (obj instanceof EJReportRunner)
            {
                return (EJReportRunner) obj;
            }
            else
            {
                throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.INVALID_REPORT_RUNNER_NAME,
                        _reportRunnerClassName, "EJReportRunner"));
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.UNABLE_TO_CREATE_REPORT_RUNNER,
                    _reportRunnerClassName), e);
        }
        catch (InstantiationException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.UNABLE_TO_CREATE_REPORT_RUNNER,
                    _reportRunnerClassName), e);
        }
        catch (IllegalAccessException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.UNABLE_TO_CREATE_REPORT_RUNNER,
                    _reportRunnerClassName), e);
        }
    }

    public String getVersion()
    {
        return _version;
    }

    public void setVersion(String version)
    {
        _version = version;
    }

    public String getConnectionFactoryClassName()
    {
        return _connectionFactoryClassName;
    }

    public void setConnectionFactoryClassName(String className)
    {
        _connectionFactoryClassName = className;

        if (className == null || className.trim().length() == 0)
        {
            _connectionFactoryClassName = null;
        }

        try
        {
            _connectionFactoryClassName = className;

            Class<?> factoryClass = Class.forName(className);
            Object obj = factoryClass.newInstance();

            if (obj instanceof EJReportConnectionFactory)
            {
                EJCoreReportManagedConnectionFactory.getInstane().setConnectionFactory((EJReportConnectionFactory) obj);
            }
            else
            {
                throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.INVALID_TRANSACTION_FACTORY));
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_TRANSACTION_FACTORY, className), e);
        }
        catch (InstantiationException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_TRANSACTION_FACTORY, className), e);
        }
        catch (IllegalAccessException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_TRANSACTION_FACTORY, className), e);
        }
    }

    public String getTranslatorClassName()
    {
        return _translatorClassName;
    }

    public void setTranslatorClassName(String className)
    {

        _translatorClassName = className;

        if (className == null || className.trim().length() == 0)
        {
            _translatorClassName = null;
        }

        if (className == null || className.trim().length() == 0)
        {
            return;
        }

        try
        {
            Class<?> rendererClass = Class.forName(className);
            Object obj = rendererClass.newInstance();

            if (obj instanceof EJReportTranslator)
            {
                _applicationTranslator = (EJReportTranslator) obj;
            }
            else
            {
                throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(EJReportFrameworkMessage.INVALID_TRANSLATOR_NAME,
                        className, "ITranslator"));
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_APPLICATION_TRANSLATOR, className), e);
        }
        catch (InstantiationException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_APPLICATION_TRANSLATOR, className), e);
        }
        catch (IllegalAccessException e)
        {
            throw new EJReportRuntimeException(EJReportMessageFactory.getInstance().createMessage(
                    EJReportFrameworkMessage.UNABLE_TO_CREATE_APPLICATION_TRANSLATOR, className), e);
        }
    }

    public EJReportTranslator getApplicationTranslator()
    {
        return _applicationTranslator;
    }

    public EJReportVisualAttributeContainer getVisualAttributesContainer()
    {
        return _visualAttributeContainer;
    }

    /**
     * Retrieve a <code>Collection</code> containing all package names where
     * report definition files can be found
     * 
     * @return A <code>Collection</code> containing report package names
     */
    public Collection<String> getReportPackageNames()
    {
        return _reportPackageNames;
    }

    /**
     * Adds a given package name to the list of report package names
     * 
     * @param packageName
     *            The package name to add
     * @throws NullPointerException
     *             if the package name passed is either null or of zero length
     */
    public void addReportPackageName(String packageName)
    {
        if (packageName == null || packageName.trim().length() == 0)
        {
            throw new NullPointerException("The package name passed to addReportPackageName is either null or of zero length");
        }
        _reportPackageNames.add(packageName);
    }

    /**
     * Checks this properties object and indicates if it is valid
     * <p>
     * An Invalid property file could be for example that it has renderers
     * defined that do not exist. If this is the case, then any form based upon
     * these properties will not run correctly and should therefore not be
     * loaded
     * 
     * @return
     */
    public boolean isValid()
    {

        return true;
    }

    public Collection<EJApplicationLevelParameter> getAllRuntimeLevelParameters()
    {
        return _runtimeLevelParameters;
    }

    public void addRuntimeLevelParameter(EJApplicationLevelParameter parameter)
    {
        if (parameter != null)
        {
            _runtimeLevelParameters.add(parameter);
        }
    }

    public EJApplicationLevelParameter getRuntimeLevelParameter(String name)
    {
        for (EJApplicationLevelParameter parameter : _runtimeLevelParameters)
        {
            if (parameter.getName().equals(name))
            {
                return parameter;
            }
        }
        return null;
    }

    public void removeRuntimeLevelParameter(EJApplicationLevelParameter parameter)
    {
        _runtimeLevelParameters.remove(parameter);
    }

    public boolean containsRuntimeLevelParameter(String name)
    {
        for (EJApplicationLevelParameter parameter : _runtimeLevelParameters)
        {
            if (parameter.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public EJReportConnectionFactory getConnectionFactory()
    {
        return EJCoreReportManagedConnectionFactory.getInstane().getConnectionFactory();
    }

    public void copyRuntimeLevelParameters(EJReportFrameworkManager frameworkManager)
    {
        for (EJApplicationLevelParameter param : _runtimeLevelParameters)
        {
            EJApplicationLevelParameter parameter = new EJApplicationLevelParameter(param.getName(), param.getDataType());
            parameter.setValue(param.getValue());
            frameworkManager.addRuntimeLevelParameter(parameter);
        }
    }

}
