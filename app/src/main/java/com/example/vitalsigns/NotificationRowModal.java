package com.example.vitalsigns;

public class NotificationRowModal {

    public String content ;
    public  String priority;

    NotificationRowModal (String a , int b) {

        content=a;

        if (b==0){
            priority = "Low";
        }else if (b==1) {
            priority =  "Medium";
        }else  if (b==2) {
            priority = "High";
        }
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getPriority() {
        return priority;
    }
}
