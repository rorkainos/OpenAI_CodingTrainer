package org.example.azure;

import java.util.List;

public record AzureProgramDetails(List<String> filesAbsolutePaths, List<String> fileContents) {
}
