package com.mjc.school.controller.implementation;

import com.mjc.school.service.implementation.Dto.ResponseDto;
import com.mjc.school.service.interfaces.ModelDtoInterface;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Scanner;
@Component
public class View {
    private final Scanner scanner;

    private View(){
        scanner = new Scanner(System.in);
    }

    @PreDestroy
    private void destroy(){
        scanner.close();
    }

    private String getInput(){
        return scanner.nextLine();
    }

    public String inputCommand() {
        System.out.print(
                """
                        __________________________________________________
                        Enter the number of operation:
                        
                        1 - View all news      | 2 - View all author's
                        3 - View news by id    | 4 - View author by id
                        5 - Create news        | 6 - Create author
                        7 - Update news        | 8 - Update author
                        9 - Delete news by id  | 10 - Delete author by id
                        
                                             0 - Exit
                        __________________________________________________
                        """
        );
        return getInput();
    }

    public String inputId(){
        System.out.println("Enter id:");
        return getInput();
    }

    public String inputName(){
        System.out.println("Enter name:");
        return getInput();
    }

    public String inputTitle(){
        System.out.println("Enter title:");
        return getInput();
    }

    public String inputContent(){
        System.out.println("Enter content:");
        return getInput();
    }

    public String inputAuthorId(){
        System.out.println("Enter author id:");
        return getInput();
    }

    public void showResponse(ResponseDto responseDto){
        if(responseDto.getStatus().equals("OK")) {
            responseDto.getResultSet().forEach(this::showModelMassage);
        } else {
            showErrorMassage(responseDto);
        }
    }

    private void showModelMassage(ModelDtoInterface modelDtoInterface){
        System.out.println(modelDtoInterface);
    }

    public void showErrorMassage(ResponseDto responseDto){
        System.out.println(responseDto.getStatus()+"\nError code: "+
                responseDto.getError().getCode()+" Message: "+
                responseDto.getError().getMessage());
    }

    public void showUnsupportedCommandMassage(){
        System.out.println("Command not found");
    }
}