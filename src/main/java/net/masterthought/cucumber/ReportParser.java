package net.masterthought.cucumber;

import com.google.gson.Gson;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.FeatureBehave;
import net.masterthought.cucumber.json.FeatureCucumber;
import net.masterthought.cucumber.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportParser {

    private Map<String, List<Feature>> jsonReportFiles;

    public ReportParser(List<String> jsonReportFiles) throws IOException {
        this.jsonReportFiles = parseJsonResults(jsonReportFiles, false);
    }

    public ReportParser(List<String> jsonReportFiles, boolean behaveMode) throws IOException {
        this.jsonReportFiles = parseJsonResults(jsonReportFiles, behaveMode);
    }

    public Map<String, List<Feature>> getFeatures() {
        return jsonReportFiles;
    }

    private Map<String, List<Feature>> parseJsonResults(List<String> jsonReportFiles, boolean behaveMode) throws IOException {
        Map<String, List<Feature>> featureResults = new LinkedHashMap<String, List<Feature>>();
        for (String jsonFile : jsonReportFiles) {
            String fileContent = Util.readFileAsString(jsonFile);
            if (Util.isValidCucumberJsonReport(fileContent)) {
                Feature[] features;
                if (behaveMode) {
                    features = new Gson().fromJson(Util.U2U(fileContent), FeatureBehave[].class);                    
                }
                else {
                    features = new Gson().fromJson(Util.U2U(fileContent), FeatureCucumber[].class);
                }
                featureResults.put(jsonFile, Arrays.asList(features));
            }
        }

        return featureResults;
    }
}
