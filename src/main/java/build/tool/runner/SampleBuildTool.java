package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolContext;
import io.ballerina.tools.diagnostics.DiagnosticFactory;
import io.ballerina.tools.diagnostics.DiagnosticInfo;
import io.ballerina.tools.diagnostics.DiagnosticSeverity;
import io.ballerina.tools.diagnostics.Location;
import io.ballerina.tools.text.LinePosition;
import io.ballerina.tools.text.LineRange;
import io.ballerina.tools.text.TextRange;

import java.nio.file.Path;

public class SampleBuildTool implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        Path absFilePath = toolContext.currentPackage().project().sourceRoot().resolve(toolContext.filePath());
        if (!absFilePath.toFile().exists()) {
            DiagnosticInfo diagnosticInfo = new DiagnosticInfo("001",
                "The provided filePath does not exist", DiagnosticSeverity.ERROR);
            toolContext.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, new NullLocation()));
        }
        System.out.println("Running sample build tool: " + toolContext.toolId());
        System.out.println("Cache created at: " + toolContext.cachePath());
        System.out.println("Output to: " + toolContext.outputPath());
    }

    @Override
    public String toolName() {
        return "dummy-tool";
    }

    private static class NullLocation implements Location {
        @Override
        public LineRange lineRange() {
            LinePosition from = LinePosition.from(0, 0);
            return LineRange.from("Dummy sample build tool", from, from);
        }

        @Override
        public TextRange textRange() {
            return TextRange.from(0, 0);
        }
    }
}
