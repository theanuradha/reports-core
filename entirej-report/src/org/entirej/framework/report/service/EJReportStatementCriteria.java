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
package org.entirej.framework.report.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.entirej.framework.report.EJReportBlockItem;
import org.entirej.framework.report.EJReportQueryBlock;
import org.entirej.framework.report.internal.EJReportDefaultServicePojoHelper;

public class EJReportStatementCriteria implements Serializable
{
    private EJReportQueryBlock           _block;
    private List<EJReportRestriction<?>> _restrictions;

    public EJReportStatementCriteria()
    {
        this(null);
    }

    public EJReportQueryBlock getBlock()
    {
        return _block;
    }

    public void setBlock(EJReportQueryBlock block)
    {
        _block = block;
    }

    /**
     * Creates a <code>EJStatementCriteria</code> object for the given block
     * 
     * @param block
     *            The block for which the criteria should be used
     */
    public EJReportStatementCriteria(EJReportQueryBlock block)
    {
        _block = block;
        _restrictions = new ArrayList<EJReportRestriction<?>>();
    }

    /**
     * Indicates if the given restriction has been made for a block service item
     * <p>
     * A check will be made for a block service item within the block with the
     * same name as the provided restrictions block item name. If there is an
     * item on the block with the same name as this restrictions block item name
     * which has its Block Service Item flag set to <code>true</code>, then this
     * method will return <code>true</code>, otherwise <code>false</code>
     * restriction is
     * 
     * @param restriction
     *            The restriction to check
     * @return <code>true</code> if there is a Block Service Item with the same
     *         name as the restrictions block item name, otherwise
     *         <code>false</code>
     */
    private boolean isRestrictionForBlockServiceItem(EJReportRestriction<?> restriction)
    {
        if (_block == null)
        {
            return true;
        }

        if (restriction.getBlockItemName() == null)
        {
            return false;
        }

        // check if there is a field or name for this restriction
        EJReportBlockItem item = null;
        for (EJReportBlockItem current : _block.getItems())
        {
            if (current.getFieldName() != null && current.getFieldName().equals(restriction.getFieldName()))
            {
                item = current;
                break;
            }
        }

        // if found: check if it is a block service item
        if (item == null || (!item.isBlockServiceItem()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns the ServicePojoHelper for the block being queried
     * 
     * @return
     */
    EJReportDefaultServicePojoHelper getServicePojoHelper()
    {
        if (_block == null)
        {
            return null;
        }

        return _block.getServicePojoHelper();
    }

    /**
     * This is a convenience method that returns the name of the block with
     * which this query criteria will be used
     * 
     * @return The name of the block to which this query criteria belongs
     */
    public String getBlockName()
    {
        if (_block == null)
        {
            return null;
        }

        return _block.getName();
    }

    /**
     * Adds a {@link EJReportRestriction} to this <code>QueryCriteria</code>
     * 
     * @param restriction
     *            The restriction to add
     */
    public void add(EJReportRestriction<?> restriction)
    {
        if (restriction != null)
        {
            if (_block != null && _block.containsItem(restriction.getBlockItemName()))
            {
                EJReportBlockItem item = _block.getItem(restriction.getBlockItemName());
                if (item != null)
                {
                    restriction.setFieldName(item.getFieldName());
                }
            }
            _restrictions.add(restriction);
        }
    }

    /**
     * Removes the given restriction from this query criteria
     * 
     * @param restriction
     *            The restriction to remove
     */
    public void remove(EJReportRestriction<?> restriction)
    {
        if (restriction != null)
        {
            _restrictions.remove(restriction);
        }
    }

    /**
     * Remove the restriction with the given block item name from this query
     * criteria
     * 
     * @param restrictionName
     *            The block item name of the restriction to remove
     */
    public void removeRestriction(String restrictionName)
    {
        if (restrictionName != null)
        {
            Iterator<EJReportRestriction<?>> iti = _restrictions.iterator();
            while (iti.hasNext())
            {
                EJReportRestriction<?> restriction = iti.next();
                if (restriction.getBlockItemName().equalsIgnoreCase(restrictionName))
                {
                    iti.remove();
                    return;
                }
            }
        }
    }

    /**
     * Returns a <code>List</code> of <code>Restriction</code> within this
     * <code>EJStatementCriteria</code>
     * 
     * @return A list of all restrictions
     */
    public List<EJReportRestriction<?>> getAllRestrictions()
    {
        return _restrictions;
    }

    /**
     * Returns a <code>List</code> of <code>EJRestriction</code> within this
     * <code>EJStatementCriteria</code> that have been set for Block Service
     * Items
     * 
     * @return A list of all restrictions for block service items only
     */
    public List<EJReportRestriction<?>> getBlockServiceItemRestrictions()
    {
        ArrayList<EJReportRestriction<?>> blockServiceRestrictions = new ArrayList<EJReportRestriction<?>>();
        for (EJReportRestriction<?> restriction : _restrictions)
        {
            if (isRestrictionForBlockServiceItem(restriction))
            {
                blockServiceRestrictions.add(restriction);
            }
        }
        return blockServiceRestrictions;
    }

    /**
     * Returns a <code>Restriction</code> for the given block item name
     * 
     * The name of the <code>Restriction</code> will be the name defined for the
     * block item
     * <p>
     * The Restriction's value can be retrieved and checked. See
     * {@link EJReportRestriction} to see how to retrieve a value for the given
     * Restriction
     * 
     * @param name
     *            The block item name of the <code>Restriction</code> to
     *            retrieve
     * @return The <code>Restriction</code> with the given block item name or
     *         <code>null</code> if there is no <code>Restriction</code> with
     *         the given block item name
     * 
     */
    public EJReportRestriction<?> getRestriction(String blockItemName)
    {
        for (EJReportRestriction<?> restriction : _restrictions)
        {
            if (restriction.getBlockItemName().equalsIgnoreCase(blockItemName))
            {
                return restriction;
            }
        }

        // there was no restriction with the given name
        return null;
    }

    /**
     * Indicates if this query criteria contains a restriction for the given
     * item name
     * 
     * The name of the <code>Restriction</code> will be the name defined for the
     * block item
     * 
     * @param name
     *            The block item name of the <code>Restriction</code> to
     *            retrieve
     * @return <code>true</code> if there is a restriction with the given name,
     *         otherwise <code>false</code>
     * 
     */
    public boolean containsRestriction(String blockItemName)
    {
        for (EJReportRestriction<?> restriction : _restrictions)
        {
            if (restriction.getBlockItemName().equalsIgnoreCase(blockItemName))
            {
                return true;
            }
        }

        // there was no restriction with the given name
        return false;
    }

    /**
     * This is a short-cut method to set a restrictions alias. If there is no
     * restriction for the given item, then nothing will be set.
     * <p>
     * This allows block service developers to set aliases on restrictions
     * without having to first check if a restriction actually exists.
     * Restrictions are only passed if a user or the framework has set a value
     * for the given item so it is mandatory to first check if a restriction
     * exists before setting an alias for it.
     * <p>
     * Use this method instead of having all the checks within block service
     * code.
     * 
     * @param restrictionName
     * @param alias
     */
    public void setRestrictionAlias(String restrictionName, String alias)
    {
        EJReportRestriction<?> restriction = getRestriction(restrictionName);
        if (restriction != null)
        {
            restriction.setAlias(alias);
        }
    }

    public String getCacheKey()
    {
        StringBuilder builder = new StringBuilder("|");
        for (EJReportRestriction<?> restriction : _restrictions)
        {
            builder.append(restriction.getCacheKey()).append("|");
        }

        return builder.toString();
    }
}
