package build.tool.runner;

import io.ballerina.projects.buildtools.CodeGeneratorTool;
import io.ballerina.projects.buildtools.ToolConfig;
import io.ballerina.projects.buildtools.ToolContext;

@ToolConfig(name = "sub_cmd_a", subcommands = {SubCmdC.class})
public class SubCmdA implements CodeGeneratorTool {
    @Override
    public void execute(ToolContext toolContext) {
        toolContext.println("Running sub command A");
    }
}
