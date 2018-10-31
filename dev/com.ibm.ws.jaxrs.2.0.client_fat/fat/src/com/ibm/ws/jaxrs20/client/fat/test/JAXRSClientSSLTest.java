/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jaxrs20.client.fat.test;

import java.util.HashMap;
import java.util.Map;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.ShrinkHelper;

import componenttest.annotation.Server;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;

@RunWith(FATRunner.class)
public class JAXRSClientSSLTest extends AbstractTest {

    @Server("jaxrs20.client.JAXRSClientSSLTest")
    public static LibertyServer server;

    private final static String appname = "jaxrsclientssl";
    private final static String target = appname + "/ClientTestServlet";

    @BeforeClass
    public static void setUp() throws Exception {
        WebArchive app = ShrinkHelper.defaultDropinApp(server, appname,
                                                       "com.ibm.ws.jaxrs20.client.JAXRSClientSSL.client",
                                                       "com.ibm.ws.jaxrs20.client.JAXRSClientSSL.service");

        // Make sure we don't fail because we try to start an
        // already started server
        try {
            server.startServer(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (server != null) {
            server.stopServer();
        }
    }

    @Before
    public void preTest() {
        serverRef = server;
    }

    @After
    public void afterTest() {
        serverRef = null;
    }

    @Test
    public void testClientBasicSSL_ClientBuilder() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "alex");
        this.runTestOnServer(target, "testClientBasicSSL_ClientBuilder", p, "[Basic Resource]:alex");
    }

    @Test
    public void testClientBasicSSL_Client() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "alex");
        this.runTestOnServer(target, "testClientBasicSSL_Client", p, "[Basic Resource]:alex");
    }

    @Test
    public void testClientBasicSSL_WebTarget() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "alex");
        this.runTestOnServer(target, "testClientBasicSSL_WebTarget", p, "[Basic Resource]:alex");
    }

//    @Test
    public void testClientBasicSSL_InvalidSSLRef() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "alex");
        this.runTestOnServer(target, "testClientBasicSSL_InvalidSSLRef", p, "the SSL configuration reference \"invalidSSLConfig\" is invalid.");
    }

    @Test
    public void testClientBasicSSL_CustomizedSSLContext() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "alex");
        p.put("SERVER_CONFIG_DIR", server.getServerRoot());
        this.runTestOnServer(target, "testClientBasicSSL_CustomizedSSLContext", p, "unable to find valid certification path to requested target");
    }

    @Test
    public void testClientLtpaHander_ClientNoTokenWithSSL() throws Exception {
        Map<String, String> p = new HashMap<String, String>();
        p.put("param", "jordan");
        this.runTestOnServer(target, "testClientLtpaHander_ClientNoTokenWithSSL", p, "[Basic Resource]:jordan");
    }
}
