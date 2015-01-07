package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {
    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public ExceptionHandler(Activity context) {
        myContext = context;
    }

    public void uncaughtException(Thread thread, final Throwable exception) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setMessage("OH ZEUS!!! You have encountered an error. \nSend an error report? (Please, please do)");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                StringWriter stackTrace = new StringWriter();
                exception.printStackTrace(new PrintWriter(stackTrace));
                StringBuilder errorReport = new StringBuilder();
                errorReport.append("************ CAUSE OF ERROR ************\n\n");
                errorReport.append(stackTrace.toString());

                errorReport.append("\n************ DEVICE INFORMATION ***********\n");
                errorReport.append("Brand: ");
                errorReport.append(Build.BRAND);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Device: ");
                errorReport.append(Build.DEVICE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Model: ");
                errorReport.append(Build.MODEL);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Id: ");
                errorReport.append(Build.ID);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Product: ");
                errorReport.append(Build.PRODUCT);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("\n************ FIRMWARE ************\n");
                errorReport.append("SDK: ");
                errorReport.append(Build.VERSION.SDK);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Release: ");
                errorReport.append(Build.VERSION.RELEASE);
                errorReport.append(LINE_SEPARATOR);
                errorReport.append("Incremental: ");
                errorReport.append(Build.VERSION.INCREMENTAL);
                errorReport.append(LINE_SEPARATOR);

                Intent emailIntent = createErrorIntent(errorReport);

                try{
                    myContext.startActivity(emailIntent.createChooser(emailIntent, "Choose an email service from..."));
                } catch (ActivityNotFoundException ex){
                    Toast.makeText(myContext, "Cannot send error report: No email app installed", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(10);
            }
        });
        builder.create().show();
    }

    private Intent createErrorIntent(StringBuilder errorReport){
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_SUBJECT, "Error Report Labyrinth of Before");
        email.putExtra(Intent.EXTRA_TEXT, errorReport.toString());

        return email;

    }

}