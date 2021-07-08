package me.thevipershow.artbot;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Getter
public final class TokenProvider {

    private final String token;

    @Nullable
    private String provideToken(final @NotNull String filename) throws IllegalArgumentException {

        // for static access, uses the class name directly
        final InputStream is = TokenProvider.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            throw new IllegalArgumentException("The token file could not be read.");
        }
        final String result = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        if (result.split("\\r?\\n").length != 1) {
            throw new IllegalArgumentException("File should only contain one token.");
        }
        return result.stripTrailing();
    }

    public TokenProvider(final @NotNull String filename) throws IllegalArgumentException {
        this.token = provideToken(filename);
    }
}
