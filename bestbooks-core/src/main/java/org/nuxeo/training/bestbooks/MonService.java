package org.nuxeo.training.bestbooks;

import java.util.Set;

public interface MonService {
    /** Add some methods here. **/

    Set<String> getActions();

    String run(String action);
}
