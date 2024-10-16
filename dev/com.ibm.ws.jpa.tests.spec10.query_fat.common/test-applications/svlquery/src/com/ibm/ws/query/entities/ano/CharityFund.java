/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.query.entities.ano;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.ibm.ws.query.entities.interfaces.ICharityFund;

@Embeddable
public class CharityFund implements ICharityFund, java.io.Serializable, Cloneable {
    private static final long serialVersionUID = 3076839517511408536L;

    @Column(length = 30)
    public String charityName;
    public Double charityAmount;

    public CharityFund() {
        super();
    }

    public CharityFund(String charityName, Double charityAmount) {
        super();
        this.charityName = charityName;
        this.charityAmount = charityAmount;
    }

    @Override
    public Double getCharityAmount() {
        return charityAmount;
    }

    @Override
    public void setCharityAmount(Double charityAmount) {
        this.charityAmount = charityAmount;
    }

    @Override
    public String getCharityName() {
        return charityName;
    }

    @Override
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    @Override
    public String toString() {
        return getCharityName() + ", " + getCharityAmount();
    }

    @Override
    public boolean equals(ICharityFund arg) {
        return arg.getCharityName() == this.getCharityName() && arg.getCharityAmount() == this.getCharityAmount();
    }

}
