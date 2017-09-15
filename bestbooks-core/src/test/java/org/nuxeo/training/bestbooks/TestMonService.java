package org.nuxeo.training.bestbooks;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy("org.nuxeo.training.bestbooks.bestbooks-core")
public class TestMonService {

    @Inject
    protected MonService monservice;

    @Test
    public void testService() {
        assertNotNull(monservice);
    }

    @Test
    public void testRun() {
        Assert.assertTrue(monservice.run().startsWith("Contenu"));
    }
}
