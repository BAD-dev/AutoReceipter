package com.github.autoreceipter.ocr;

import com.github.autoreceipter.*;
/**
 * Created by Georg on 4/5/2016.
 */
public class ImageProcessing {

    Client restClient;
    String convertedFile;

    public ImageProcessing() {
        restClient = new Client();
        // replace with 'https://cloud.ocrsdk.com' to enable secure connection
        restClient.serverUrl = "http://cloud.ocrsdk.com";
        restClient.applicationId = ClientSettings.APPLICATION_ID;
        restClient.password = ClientSettings.PASSWORD;
    }

    public void ProcessFile(String file) throws Exception {
        ProcessingSettings settings = new ProcessingSettings();
        settings.setLanguage("English");
        settings.setOutputFormat(ProcessingSettings.OutputFormat.txt);

        System.out.println("Uploading file..");
        Task task = restClient.processImage(file, settings);
        waitAndDownloadResult(task, "scanned/test6.txt");
        convertedFile = restClient.convertedFile;
    }

    /**
     * Wait until task processing finishes
     */
    private Task waitForCompletion(Task task) throws Exception {
        // Note: it's recommended that your application waits
        // at least 2 seconds before making the first getTaskStatus request
        // and also between such requests for the same task.
        // Making requests more often will not improve your application performance.
        // Note: if your application queues several files and waits for them
        // it's recommended that you use listFinishedTasks instead (which is described
        // at http://ocrsdk.com/documentation/apireference/listFinishedTasks/).
        Thread.sleep(5000);
        while (task.isTaskActive()) {

            Thread.sleep(5000);
            System.out.println("Waiting..");
            task = restClient.getTaskStatus(task.Id);
        }
        return task;
    }

    /**
     * Wait until task processing finishes and download result.
     */
    private void waitAndDownloadResult(Task task, String outputPath) throws Exception {
        task = waitForCompletion(task);

        if (task.Status == Task.TaskStatus.Completed) {
            System.out.println("Downloading..");
            restClient.downloadResult(task, outputPath);
            System.out.println("Ready");
        } else if (task.Status == Task.TaskStatus.NotEnoughCredits) {
            System.out.println("Not enough credits to process document. "
                    + "Please add more pages to your application's account.");
        } else {
            System.out.println("Task failed");
        }

    }

    public String getConvertedFile() {
        return convertedFile;
    }

}
