package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolConfig;
import io.ballerina.projects.buildtools.ToolContext;
import io.ballerina.tools.diagnostics.Location;
import io.ballerina.tools.text.LinePosition;
import io.ballerina.tools.text.LineRange;
import io.ballerina.tools.text.TextRange;

import java.util.Locale;

@ToolConfig(name = "caps")
public class WriteCapsBuildTool implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        toolContext.println("Running write caps tool: " + toolContext.toolId());
        toolContext.println(toolContext.options().get("writeValue").value().toString().toUpperCase(Locale.ROOT));
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
