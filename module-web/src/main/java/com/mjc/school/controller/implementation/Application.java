package com.mjc.school.controller.implementation;

import com.mjc.school.controller.annatation.CommandParam;
import com.mjc.school.service.implementation.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Application {
    boolean continueLoop;
    View view;
    AuthorController authorController;
    NewsController newsController;
    @Autowired
    private Application(View view,
                       AuthorController authorController,
                       NewsController newsController) {
        this.continueLoop = true;
        this.view = view;
        this.authorController = authorController;
        this.newsController = newsController;
    }

    public void runLoop(){
        while(continueLoop) {
            controlMenuView();
        }
    }

    private void controlMenuView(){
        int index = validateCommandInput(view.inputCommand());
        ResponseDto response = new ResponseDto();
        if(index == -1) {incorrectCommand(); return;}
        else if(index == 0) {exit(); return;}
        else if(index == 1) response = newsController.readAll();
        else if(index == 2) response = authorController.readAll();
        else if(index == 3) response = newsController.readById();
        else if(index == 4) response = authorController.readById();
        else if(index == 5) response = newsController.create();
        else if(index == 6) response = authorController.create();
        else if(index == 7) response = newsController.update();
        else if(index == 8) response = authorController.update();
        else if(index == 9) response = newsController.delete();
        else if(index == 10) response = authorController.delete();
        view.showResponse(response);
    }
    private int validateCommandInput(@CommandParam String input){
        try {
            int index = Integer.parseInt(input);
            if(index < 0 || index > 10) return -1;
            return index;
        } catch(Exception e){
            return -1;
        }
    }
    private void exit(){
        continueLoop = false;
    }
    private void incorrectCommand(){
        view.showUnsupportedCommandMassage();
    }

}
