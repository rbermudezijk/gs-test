import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class TagAnalizer {
    private final Pattern tagRegex = Pattern.compile("<([^<>]+)>([^<>]+)<\\/([^<>]+)>");
    private String line;

    public TagAnalizer(String line) {
        this.line = line;
    }

    public Boolean validatesTag(String openTag, String closeTag) {
        return openTag.compareTo(closeTag) == 0;
    }

    public void run() {
        Matcher res = this.tagRegex.matcher(this.line);
        Integer counter = 0;

        while (res.find()) {
            String content = res.group(2);
            if (validatesTag(res.group(1), res.group(3))) {
                System.out.println(content);
                counter++;
            }
        }

        if (counter == 0) {
            System.out.println("None");
        }
    }
}

public class TagAnalizerSolution {
    public static List<String> getInputLines() {
        Scanner scan = new Scanner(System.in);
        Integer numberOfLines = scan.nextInt();
        scan.nextLine();
        List<String> lines = new ArrayList<String>();
        for (int i = 0; i < numberOfLines; i++) {
            lines.add(scan.nextLine());
        }
        scan.close();
        return lines;
    }

    public static void main(String[] args) {
        List<String> inputLines = getInputLines();
        inputLines.forEach(line -> new TagAnalizer(line).run());
    }
}