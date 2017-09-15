package org.nuxeo.training.bestbooks;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy("org.nuxeo.training.bestbooks.bestbooks-core")
public class TestCheckUserPermission {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    /*
    @Test
    public void shouldCallTheOperation() throws OperationException {
        OperationContext ctx = new OperationContext(session);
        DocumentModel doc = (DocumentModel) automationService.run(ctx, CheckUserPermission.ID);
        assertEquals("/", doc.getPathAsString());
    }

    @Test
    public void shouldCallWithParameters() throws OperationException {
        final String path = "/default-domain";
        OperationContext ctx = new OperationContext(session);
        Map<String, Object> params = new HashMap<>();
        params.put("path", path);
        DocumentModel doc = (DocumentModel) automationService.run(ctx, CheckUserPermission.ID, params);
        assertEquals(path, doc.getPathAsString());
    }
    */

    @Before
    public void setup() {

    }

    @Test
    public void setCorrectContextVariable() throws OperationException {
        // Créer l'enveloppe du document
        DocumentModel doc = session.createDocumentModel("Folder");
        doc.setPropertyValue("dc:title", "mon-dossier");

        // Créer le document en base
        doc = session.createDocument(doc);
        session.save();

        // Récupération de l'id du doc
        String docId = doc.getId();

        // Récupérer un document avec son ID
        //DocumentModel docTwo = session.getDocument(new IdRef(docId));


        OperationContext ctx = new OperationContext(session);
        String ctxVarName = "hasPermission";
        Map<String, Object> params = new HashMap<>();
        params.put("username", "userNotExisting");
        params.put("docId", docId);
        params.put("permission", "permission");
        params.put("ctxVarName", ctxVarName);

        automationService.run(ctx, CheckUserPermission.ID, params);

        Assert.assertTrue(ctx.containsKey(ctxVarName));
        Assert.assertEquals(false, ctx.get(ctxVarName));
    }
}
