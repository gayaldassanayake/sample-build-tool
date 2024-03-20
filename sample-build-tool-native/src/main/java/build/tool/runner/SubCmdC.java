package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolConfig;
import io.ballerina.projects.buildtools.ToolContext;

@ToolConfig(name = "sub_cmd_c")
public class SubCmdC implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        toolContext.println("Running sub command C");
    }
}
