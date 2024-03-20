package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolConfig;
import io.ballerina.projects.buildtools.ToolContext;

@ToolConfig(name = "sub_cmd_b")
public class SubCmdB implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        toolContext.println("Running sub command B");
    }
}
