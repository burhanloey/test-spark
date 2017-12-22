package provider;

import javax.inject.Provider;
import java.util.regex.Pattern;

public class IntegerRegexProvider implements Provider<Pattern> {

    private final Pattern pattern;

    public IntegerRegexProvider() {
        pattern = Pattern.compile("[0-9]+");
    }

    @Override
    public Pattern get() {
        return pattern;
    }
}
