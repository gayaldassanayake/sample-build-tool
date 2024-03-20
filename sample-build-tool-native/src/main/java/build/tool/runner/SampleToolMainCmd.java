package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolConfig;
import io.ballerina.projects.buildtools.ToolContext;
import io.ballerina.tools.diagnostics.DiagnosticFactory;
import io.ballerina.tools.diagnostics.DiagnosticInfo;
import io.ballerina.tools.diagnostics.DiagnosticSeverity;
import io.ballerina.tools.diagnostics.Location;
import io.ballerina.tools.text.LinePosition;
import io.ballerina.tools.text.LineRange;
import io.ballerina.tools.text.TextRange;

import java.nio.file.Path;

/**
 * Sample tool to demonstrate the usage of the build tool API.
 */
// Interface CodeGeneratorTool is used to define a custom build tool command.
// The annotation "ToolConfig" is used to define the command name and subcommands.
// If there are no subcommands, "subcommands" can be omitted.
@ToolConfig(name = "sample_cmd", subcommands = {SubCmdA.class, SubCmdB.class})
public class SampleToolMainCmd implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        Path absFilePath = toolContext.currentPackage().project().sourceRoot().resolve(toolContext.filePath());
        // Report diagnostics
        if (!absFilePath.toFile().exists()) {
            DiagnosticInfo diagnosticInfo = new DiagnosticInfo("001",
                "The provided filePath does not exist", DiagnosticSeverity.ERROR);
            // Report the diagnostic. This will be printed to the console after the execution of the tool.
            // If the diagnostic is an error, it will result in a build failure.
            toolContext.reportDiagnostic(DiagnosticFactory.createDiagnostic(diagnosticInfo, new NullLocation()));
        }

        // Access optional configurations and there locations
        ToolContext.Option modeOption = toolContext.options().get("mode");
        if (!modeOption.value().toString().equals("client")) {
            DiagnosticInfo modeDiagnostic = new DiagnosticInfo("002", "Modes that are not client " +
                    "are not supported", DiagnosticSeverity.WARNING);
            toolContext.reportDiagnostic(DiagnosticFactory.createDiagnostic(modeDiagnostic, modeOption.location()));
        }

        // toolContext.println will immediately print a message to the console.
        toolContext.println("Running sample build tool: " + toolContext.toolId());
        toolContext.println("Cache created at: " + toolContext.cachePath());
        toolContext.println("Output to: " + toolContext.outputPath());
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
