package model;

import java.util.ArrayList;
import java.util.List;
import model.Step;


public class TestResult 
{
    public TestResult()
    {
        testSteps=new ArrayList<Step>();
    }
    public String testCaseName;
    public List<Step> testSteps;
    public List<String> tags;

    public String testStatus;
    public long duration;
    public String durationDesc;
    
}
