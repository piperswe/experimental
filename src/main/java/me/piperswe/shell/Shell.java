package me.piperswe.shell;

import com.google.common.io.LineReader;
import lombok.NonNull;
import me.piperswe.demogui.DemoGUIApplication;
import me.piperswe.utils.AbstractVirtualExecutionThreadService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Shell extends AbstractVirtualExecutionThreadService {
    @NonNull
    private final LineReader reader = new LineReader(new InputStreamReader(System.in));
    @NonNull
    private final Map<String, Command> commands = new HashMap<>();

    public Shell() {
        commands.put("help", (args) -> System.out.println("me.piperswe:experimental shell"));
        commands.put("echo", (args) -> System.out.println(String.join(" ", args)));
        commands.put("demogui", DemoGUIApplication.getCommand());
    }

    private boolean isQuote(char ch) {
        return ch == '\'' || ch == '"';
    }

    @NonNull
    private Optional<List<String>> parseLine(@NonNull String line) {
        var trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return Optional.empty();
        }
        var segments = new ArrayList<String>();
        var builder = new StringBuilder();
        var inQuotes = false;
        var chars = trimmed.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (inQuotes) {
                if (isQuote(ch)) {
                    inQuotes = false;
                } else {
                    builder.append(ch);
                }
            } else if (Character.isWhitespace(ch)) {
                if (!builder.isEmpty()) {
                    segments.add(builder.toString());
                    builder.setLength(0);
                }
            } else if (ch == '\\') {
                i++;
                ch = chars[i];
                builder.append(ch);
            } else if (isQuote(ch)) {
                inQuotes = true;
            } else {
                builder.append(ch);
            }
        }
        if (!builder.isEmpty()) {
            segments.add(builder.toString());
        }
        return Optional.of(segments);
    }

    private void executeCommand(@NonNull String commandName, @NonNull List<String> args) {
        if (commands.containsKey(commandName)) {
            var command = commands.get(commandName);
            command.run(args);
        } else {
            System.err.printf("Unknown command: %s%n", commandName);
        }
    }

    @Override
    public void run() {
        System.out.println("me.piperswe:experimental shell starting");
        while (true) {
            String line;
            try {
                System.out.print("experimental-shell> ");
                line = reader.readLine();
                if (line == null) {
                    return;
                }
            } catch (IOException e) {
                return;
            }
            var maybeSegments = parseLine(line);
            if (maybeSegments.isPresent()) {
                var segments = maybeSegments.get();
                if (segments.size() > 0) {
                    var command = segments.remove(0);
                    executeCommand(command, segments);
                }
            }
        }
    }
}
