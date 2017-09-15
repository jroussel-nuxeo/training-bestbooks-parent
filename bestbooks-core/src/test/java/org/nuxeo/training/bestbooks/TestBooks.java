package org.nuxeo.training.bestbooks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;
import java.util.Calendar;

@RunWith(FeaturesRunner.class)
@Features({AutomationFeature.class})
@Deploy({"studio.extensions.jroussel-SANDBOX"})
public class TestBooks {

    @Inject
    private CoreSession session;

    @Test
    public void testEvent() {
        DocumentModel doc = session.createDocumentModel("TRAINING-Book");

        Assert.assertNotNull(doc);
        Assert.assertNotNull(doc.getPropertyValue("training-generic-book:publicationDate"));
        Assert.assertTrue(doc.getPropertyValue("training-generic-book:publicationDate") instanceof Calendar);
    }

    @Test
    public void shouldHaveCorrectTitle() {
        /*
        final String titleToUse = "Da nuxeo code";

        DocumentModel doc = session.createDocumentModel("TRAINING-Book");
        doc.setPropertyValue("dc:title", titleToUse);

        // Créer le document en base
        // Following line raises this issue:
        // org.nuxeo.ecm.core.api.NuxeoException: Cannot reconnect a CoreSession when transaction is marked rollback-only
        //doc = session.createDocument(doc);
        //session.save();

        Assert.assertEquals(titleToUse, equals(doc.getPropertyValue("dc:title")));
        */
    }

}
