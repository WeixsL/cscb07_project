package com.example.myapplication;

public class Presenter {

    private Model model;
    private MainActivity view;

    public Presenter(Model model, MainActivity view) {
        this.model = model;
        this.view = view;
    }

    public void login(String email, String password) {

        model.authenticate(email, password, (User user) -> {

            if (user == null) view.failedToLogin();

            if (user instanceof Student)
                view.redirectToStudentOperations(user.getID());
            else
                view.redirectToAdminOperations(user.getID());
        });

    }


}
