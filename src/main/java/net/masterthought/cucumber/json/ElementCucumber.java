package net.masterthought.cucumber.json;

import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;

import net.masterthought.cucumber.ConfigurationOptions;
import net.masterthought.cucumber.util.Util;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.json.StepCucumber;

import static com.googlecode.totallylazy.Option.option;

public class ElementCucumber extends Element {

    private StepCucumber[] steps;

    public Sequence<StepCucumber> getSteps() {
        return Sequences.sequence(option(steps).getOrElse(new StepCucumber[]{})).realise();
    }

    public Util.Status getStatus() {
        // can be optimized to retrieve only the count of elements and not the all list
        int results = getSteps().filter(Step.predicates.hasStatus(Util.Status.FAILED)).size();
        
        if (results == 0 && ConfigurationOptions.skippedFailsBuild()) {
            results = getSteps().filter(Step.predicates.hasStatus(Util.Status.SKIPPED)).size();
        }

        if (results == 0 && ConfigurationOptions.undefinedFailsBuild()) {
            results = getSteps().filter(Step.predicates.hasStatus(Util.Status.UNDEFINED)).size();
        }
        
        return results == 0 ? Util.Status.PASSED : Util.Status.FAILED;
    }
}