package com.example.myapplication;

public class QualificationQuestions {

    public String question1;
    public String question2;
    public String question3;

    public QualificationQuestions(){}
    public QualificationQuestions(String studentType){
        if(studentType.equals("For students applying for a CS minor")){
            question1 = "Have you completed 4.0 credits?";
            question2 = "Have you finished all required CSC courses(CSCA08, CSCA48)?";
            question3 = "Have you finished all required MAT courses(one of CSCA67/MATA67, MATA22/A23, MATA30/A31/A32)?";
        }
        else if(studentType.equals("For students in the CS admission category applying for CS Major/Specialist")){
            question1 = "Have you achieved a grade point average of at least 2.5 across the following five courses: " +
                    "CSC/MATA67, CSCA48, MATA22, MATA31, MATA37?";
            question2 = "Have you achieved a grade of at least B in CSCA48?";
            question3 = "Have you achieved a grade of at least C- in two of CSC/MATA67, MATA22, MATA37?";
        }
        else if(studentType.equals("For students in other admission categories applying for CS Major/Specialist")){
            question1 = "Have you achieved at least an A- in CSC/MATA67?";
            question2 = "Have you achieved at least an A- in MATA31?";
            question3 = "Did you achieved at least an A- in both courses above at the first time that you complete those courses?";
        }
        else if(studentType.equals("For students who began at UTSC prior to 2021 applying for CS Major/Specialist")){
            question1 = "Have you retake CSC/MATA67, CSCA48, MATA22, MATA37?";
            question2 = "Have not you passed any one of the above courses?";
            question3 = "Did you gain descent grades in the courses above?";
        }
    }

}
