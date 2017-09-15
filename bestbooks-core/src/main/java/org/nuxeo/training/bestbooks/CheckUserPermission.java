package org.nuxeo.training.bestbooks;

import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.platform.usermanager.NuxeoPrincipalImpl;
import org.nuxeo.ecm.platform.usermanager.UserManager;
import org.nuxeo.runtime.api.Framework;

/**
 *
 */
@Operation(id = CheckUserPermission.ID, category = Constants.CAT_DOCUMENT, label = "checkUserPermission", description = "Describe here what your operation does.")
public class CheckUserPermission {

    public static final String ID = "Document.CheckUserPermission";

    @Context
    protected CoreSession session;

    @Context
    protected OperationContext opCtx;

    @Param(name = "username", required = true)
    protected String username;

    @Param(name = "permission", required = true)
    protected String permission;

    @Param(name = "docId", required = true)
    protected String docId;

    @Param(name = "ctxVarName", required = true)
    protected String ctxVarName;

    @OperationMethod
    public void run() {
        DocumentRef docRef = new IdRef(docId);

        // Pour récupérer un Principal, instancions et utilisons un UserManager
        UserManager userManager = Framework.getService(UserManager.class);
        NuxeoPrincipalImpl principalToTest = (NuxeoPrincipalImpl) userManager.getPrincipal(username);

        boolean hasPermission = false;
        if (principalToTest != null) {
            hasPermission = session.hasPermission(principalToTest, docRef, permission);
        }

        opCtx.put(ctxVarName, hasPermission);
    }
}
