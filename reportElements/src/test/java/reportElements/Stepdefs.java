package reportElements;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.cucumber.datatable.DataTable;
import org.apache.commons.lang.exception.ExceptionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

class ReportElements {
    public Integer getSecondBiggest(List<Integer> list) {
        // Sort list descending and eliminate duplicate elements
        List<Integer> elements = new ArrayList<>(list);
        Collections.sort(elements);
        Collections.reverse(elements);
        List<Integer> uniqueElements = elements.stream().distinct().collect(Collectors.toList());
        if (uniqueElements.size() > 1) {
            return uniqueElements.get(1);
        } else return null; // Return null if list contains only one unique element (no second largest element)
    }
}

public class Stepdefs {

    private List<Integer> reportList;
    private Integer actualElement;
    private String actualAnswer;

    @Given("Report contains following elements")
    public void report_contains_following_elements(DataTable reportList) {
        try {
            // Get table data and save them as List of integers
            this.reportList = reportList.asList(Integer.class);
        } catch (Exception e) {
            String message = ExceptionUtils.getStackTrace(e);
            if (message.contains("java.lang.NumberFormatException: For input string")) {
                this.actualAnswer = "Wrong data type - integer required";
            } else if (message.contains("java.lang.NumberFormatException: Zero length string")) {
                this.actualAnswer = "Report contains empty elements";
            } else {
                this.actualAnswer = e.getMessage();
            }
        }
    }

    @When("User searches for second biggest element")
    public void user_searches_for_second_biggest_element() {
        ReportElements report = new ReportElements();
        if (reportList != null) {
            this.actualElement = report.getSecondBiggest(reportList);
            if (this.actualElement == null) {
                this.actualAnswer = "No second biggest element";
            }
        }
    }

    @Then("User should get {int}")
    public void user_should_get(Integer expectedElement) {
        assertEquals(expectedElement, actualElement);
    }

    @Then("User should get {string}")
    public void user_should_get(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}

