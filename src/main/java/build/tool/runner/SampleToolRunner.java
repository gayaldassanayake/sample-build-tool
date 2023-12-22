package build.tool.runner;

import io.ballerina.cli.tool.CodeGeneratorTool;
import io.ballerina.projects.ToolContext;
import io.ballerina.tools.diagnostics.DiagnosticFactory;
import io.ballerina.tools.diagnostics.DiagnosticInfo;
import io.ballerina.tools.diagnostics.DiagnosticSeverity;
import io.ballerina.tools.diagnostics.Location;
import io.ballerina.tools.text.LinePosition;
import io.ballerina.tools.text.LineRange;
import io.ballerina.tools.text.TextRange;

import java.nio.file.Path;

public class SampleToolRunner implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        Path absFilePath = toolContext.currentPackage().project().sourceRoot().resolve(toolContext.filePath());
        if (!absFilePath.toFile().exists()) {
            DiagnosticInfo diagnosticInfo = new DiagnosticInfo("001",
                "The provided filePath does not exist", DiagnosticSeverity.ERROR);
            toolContext.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, new NullLocation()));
        }
        System.out.println("Running sample build tool: " + toolContext.toolType());
        System.out.println("Cache created at: " + toolContext.cachePath());
    }

    @Override
    public String toolName() {
        return "openapi";
    }

    private static class NullLocation implements Location {
        @Override
        public LineRange lineRange() {
            LinePosition from = LinePosition.from(0, 0);
            return LineRange.from("openAPI sample build tool", from, from);
        }

        @Override
        public TextRange textRange() {
            return TextRange.from(0, 0);
        }
    }
}
