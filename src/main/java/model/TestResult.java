package model;

import java.util.ArrayList;
import java.util.List;


public class TestResult 
{
    public TestResult()
    {
      
    }
    public String testCaseName;
    public List<Steps> testSteps;
    public List<String> tags;

    public String testStatus;
    public long duration;
    public String durationDesc;
    
}
