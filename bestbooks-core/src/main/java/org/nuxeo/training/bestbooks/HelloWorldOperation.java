package org.nuxeo.training.bestbooks;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.runtime.api.Framework;

/**
 * If you need to call this operation using REST-automation endpoint:
 * curl -XPOST --user Administrator:Administrator http://localhost:8080/nuxeo/api/v1/automation/HelloWorldOperation -H "Content-Type:application/json+nxrequest" -d '{}'
 *
 * If you want to make this operation visible in your Studio, go through this URL to get its JSON:
 * http://localhost:8080/nuxeo/site/automation/HelloWorldOperation
 * Then go to Studio / Settings / Registries / Automation Operations and add the JSON there nested in this:
 * {
 *    "operations": [
 *    ]
 *    }
 */
@Operation(id=HelloWorldOperation.ID, category=Constants.CAT_DOCUMENT, label="helloWorldOperation", description="Will return plain text as a binary")
public class HelloWorldOperation {

    private static final Log log = LogFactory.getLog(HelloWorldOperation.class);

    public static final String ID = "HelloWorldOperation";

    @OperationMethod
    public Blob run() {
        log.info("HelloWorldOperation has been called");

        // Retrieve String message from the Service we defined aside
        MonService monService = Framework.getService(MonService.class);
        String msg = monService.run();
        return new StringBlob(msg, "text/plain");
    }
}
