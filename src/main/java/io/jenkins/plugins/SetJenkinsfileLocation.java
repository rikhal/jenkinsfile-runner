package io.jenkins.plugins;

import hudson.model.Action;
import hudson.model.InvisibleAction;
import org.apache.commons.io.FileUtils;
import org.jenkinsci.plugins.workflow.cps.CpsFlowExecution;
import org.jenkinsci.plugins.workflow.cps.CpsFlowFactoryAction2;
import org.jenkinsci.plugins.workflow.flow.FlowDefinition;
import org.jenkinsci.plugins.workflow.flow.FlowExecutionOwner;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Loads {@code Jenkinsfile} from a specific file.
 *
 * @author Kohsuke Kawaguchi
 */
public class SetJenkinsfileLocation extends InvisibleAction implements CpsFlowFactoryAction2 {
    private final File jenkinsfile;

    public SetJenkinsfileLocation(File jenkinsfile) {
        this.jenkinsfile = jenkinsfile;
    }

    @Override
    public CpsFlowExecution create(FlowDefinition def, FlowExecutionOwner owner, List<? extends Action> actions) throws IOException {
        return new CpsFlowExecution(FileUtils.readFileToString(jenkinsfile), true, owner);
    }
}
