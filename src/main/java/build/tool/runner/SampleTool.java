package build.tool.runner;

import io.ballerina.cli.BLauncherCmd;
import picocli.CommandLine;

@CommandLine.Command(name = "dummy_tool", description = "A sample tool built for testing bal tool functionality")
public class SampleTool implements BLauncherCmd {
    @Override
    public void execute() {

    }

    @Override
    public String getName() {
        return "dummy_tool";
    }

    @Override
    public void printLongDesc(StringBuilder stringBuilder) {
        stringBuilder.append("A sample tool built for testing bal tool functionality");
    }

    @Override
    public void printUsage(StringBuilder stringBuilder) {

    }

    @Override
    public void setParentCmdParser(CommandLine commandLine) {

    }
}
