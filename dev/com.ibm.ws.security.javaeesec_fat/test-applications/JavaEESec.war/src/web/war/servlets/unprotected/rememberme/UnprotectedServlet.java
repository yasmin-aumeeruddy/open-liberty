/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
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
package web.war.servlets.unprotected.rememberme;

import web.jar.base.FlexibleBaseServlet;

public class UnprotectedServlet extends FlexibleBaseServlet {
    private static final long serialVersionUID = 1L;

    public UnprotectedServlet() {
        super("UnprotectedServlet");

        mySteps.add(new ProcessSecurityContextAuthenticateStep());
        mySteps.add(new WriteRequestBasicsStep());
        mySteps.add(new WritePrincipalStep());
    }

}