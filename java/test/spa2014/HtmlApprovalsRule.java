package spa2014;

import org.rococoa.okeydoke.Approver;
import org.rococoa.okeydoke.ApproverFactory;
import org.rococoa.okeydoke.Reporters;
import org.rococoa.okeydoke.Sources;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;

public class HtmlApprovalsRule {

    public static ApprovalsRule forSourceDir(final String srcDir) {
        return new ApprovalsRule(new ApproverFactory<Approver>() {
            @Override
            public Approver createApprover(String testName, Class<?> testClass) {
                return new Approver(testName,
                        Sources.in(new File(srcDir), testClass.getPackage()).withTypeExtension(".html"),
                        Reporters.reporter());
            }
        });
    }
}
