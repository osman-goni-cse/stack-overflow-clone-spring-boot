package learn.osman.stackoverflowclone.entity;

public class Answer {
    private String answerDetail;
    private Question question;

    public Answer(String answerDetail, Question question) {
        this.answerDetail = answerDetail;
        this.question = question;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
