package org.nuxeo.training.bestbooks.extensionspoints;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;


/**
 * Ce code va pouvoir générer un XML de ce genre :
 *
 * <monserviceaction name="action" myClass=".class"></monserviceaction>
 */
@XObject("monserviceaction")
public class MonServiceDescriptor {

    @XNode("@name")
    private String action;

    @XNode("@myClass")
    private Class<MonServiceAction> myClass;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Class<MonServiceAction> getMyClass() {
        return myClass;
    }

    public void setMyClass(Class<MonServiceAction> myClass) {
        this.myClass = myClass;
    }

}
