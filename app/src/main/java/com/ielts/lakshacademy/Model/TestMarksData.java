package com.ielts.lakshacademy.Model;

public class TestMarksData {

    String Module,exam_date,total_marks,obtain_marks,que_paper,ans_key,exam_detail_id;

    public TestMarksData(String module, String exam_date, String total_marks, String obtain_marks, String que_paper, String ans_key,String exam_detail_id) {
        Module = module;
        this.exam_date = exam_date;
        this.total_marks = total_marks;
        this.obtain_marks = obtain_marks;
        this.que_paper = que_paper;
        this.ans_key = ans_key;
        this.exam_detail_id = exam_detail_id;
    }

    public TestMarksData() {

    }

    public String getModule() {
        return Module;
    }

    public void setModule(String module) {
        Module = module;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getObtain_marks() {
        return obtain_marks;
    }

    public void setObtain_marks(String obtain_marks) {
        this.obtain_marks = obtain_marks;
    }

    public String getQue_paper() {
        return que_paper;
    }

    public void setQue_paper(String que_paper) {
        this.que_paper = que_paper;
    }

    public String getAns_key() {
        return ans_key;
    }

    public void setAns_key(String ans_key) {
        this.ans_key = ans_key;
    }

    public String getExam_detail_id() {
        return exam_detail_id;
    }

    public void setExam_detail_id(String exam_detail_id) {
        this.exam_detail_id = exam_detail_id;
    }
}
