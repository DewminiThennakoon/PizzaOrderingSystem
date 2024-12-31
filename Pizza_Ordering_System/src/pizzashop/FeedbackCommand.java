package pizzashop;

interface Command {
    void execute();
}

class FeedbackCommand implements Command {
    private String feedback;

    public FeedbackCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        System.out.println("Feedback submitted: " + feedback);
    }
}
