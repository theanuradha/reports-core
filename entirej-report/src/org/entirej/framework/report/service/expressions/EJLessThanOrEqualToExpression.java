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
package org.entirej.framework.report.service.expressions;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.entirej.framework.report.EJReportMessage;
import org.entirej.framework.report.EJReportRuntimeException;

class EJLessThanOrEqualToExpression implements Serializable
{
    private String _string;

    EJLessThanOrEqualToExpression(String string)
    {
        if (string == null)
        {
            throw new EJReportRuntimeException(new EJReportMessage("A GreaterThan expression must contain a value"));
        }

        _string = string;

    }

    public String getExpressionString(String name)
    {
        return " <= :" + name;
    }

    public int setStatementParams(PreparedStatement pstmt, int startPos) throws SQLException
    {
        pstmt.setObject(startPos, _string);
        return 1;
    }

    public Class<?> getBaseType()
    {
        return String.class;
    }

    public String toString()
    {
        return "<= " + _string;
    }
}
