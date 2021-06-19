package core.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import model.TestResult;

public class TestReportUtil {
    private static String defaultDirectory= ".report";
    private static String resultFileName()
    {
        return UUID.randomUUID().toString() + ".json";
    }
    public static void writeResultsToFile(List<TestResult> testResults)
    {
        Gson gson = new Gson();
        try {
           String resultDir = System.getProperty("user.dir")+ "/"+ defaultDirectory + "/";
           Files.createDirectories(Paths.get(resultDir));
           Files.write(Paths.get(resultDir +  resultFileName()), gson.toJson(testResults).getBytes());    

        }            
        catch (Exception e)
         {          
           e.printStackTrace();
       }
    }
    
}
