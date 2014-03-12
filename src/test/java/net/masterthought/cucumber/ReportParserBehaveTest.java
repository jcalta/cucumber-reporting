package net.masterthought.cucumber;

import static net.masterthought.cucumber.FileReaderUtil.getAbsolutePathFromResource;
import net.masterthought.cucumber.json.FeatureBehave;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportParserBehaveTest {

    @Test
    public void shouldReturnAListOfFeaturesFromABehaveReport() throws IOException {
        boolean behave = true;
        ReportParser reportParser = new ReportParser(withBehaveSingleScenarioReport(), behave);
        assertThat(reportParser.getFeatures().entrySet().size(), is(1));
        assertThat(reportParser.getFeatures().entrySet().iterator().next().getValue().get(0), is(FeatureBehave.class));
    }

    private List<String> withBehaveSingleScenarioReport() {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/behave/cc-5a.json"));
        return jsonReports;
    }

}
