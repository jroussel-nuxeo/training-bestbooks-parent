package org.nuxeo.training.bestbooks;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.event.DocumentEventTypes;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

import java.util.Calendar;

/**
 * Class used to perform the same action as SetPublicationDateEH and SetPublicationDateAC
 */
public class PublicationDateListener implements EventListener {
    @Override
    public void handleEvent(Event event) {
        DocumentEventContext docCtx = (DocumentEventContext) event.getContext();
        DocumentModel doc = docCtx.getSourceDocument();

        // First possibility to perform work only if manipulating TRAINING-Book
        if ("TRAINING-Book".equals(doc.getType())) {
            doc.setPropertyValue("training-generic-book:publicationDate", Calendar.getInstance());
        }

        // Other possibility, we test if the document has a schema
        /*
        if (doc.hasSchema("training-generic-book")) {
            doc.setPropertyValue("training-generic-book:publicationDate", Calendar.getInstance());
        }
        */


        // If I want to find all possible eventtypes
        //DocumentEventTypes.EMPTY_DOCUMENTMODEL_CREATED;
    }
}
