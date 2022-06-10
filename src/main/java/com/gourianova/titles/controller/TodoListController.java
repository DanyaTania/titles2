package com.gourianova.titles.controller;


import com.gourianova.titles.dao.Impl.TodoItemManagementInMemory;
import com.gourianova.titles.model.TodoItem;
import com.gourianova.titles.task.EnglishTitlesToSrtFormat;
import com.gourianova.titles.task.FrenchTitlesToSrtFormat;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("todocontroller")
@ViewScoped
public class TodoListController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    TodoItemManagementInMemory todoManagement;

    private List<TodoItem> todoItems;

    private TodoItem selectedItem;
    private List<TodoItem> selectedItems;

    private String name;
    private String category;


    public List<TodoItem> getTodoItems () {
        return todoManagement.getTodoItems ();
    }

    public void buttonUpdateAction () {
        todoManagement.updateTodoItem (selectedItems);
    }

    public void buttonAddAction () {

        String name2=formatTitles(name);

        TodoItem addItem = new TodoItem (name2, category, false);
        todoManagement.addTodoItem (addItem);
    }

    private String formatTitles(String name) {
        String titles = "";
        byte[] result = name.getBytes();
        for (int i = 0; i < result.length; i++) {
            char c = (char) result[i];
            titles += c;

        }
      //  System.out.println(textIn.getLanguage_of_subtitles());

        String resultOut;
     //   if (category == "English") {
      //      resultOut = new EnglishTitlesToSrtFormat(titles).getResult();
      //  } else {
            resultOut = new FrenchTitlesToSrtFormat(titles).getResult();
        //}
        //textIn.setText_title(resultOut);
        System.out.println(resultOut);

        return             resultOut;

    }

  //  }

    public List<TodoItem> getSelectedItem () {
        return selectedItems;
    }

    public void setSelectedItem ( TodoItem selectedItem ) {
        this.selectedItem = selectedItem;
    }

    public List<TodoItem> getSelectedItems () {
        return selectedItems;
    }

    public void setSelectedItems ( List<TodoItem> selectedItems ) {
        this.selectedItems = selectedItems;
    }

    public void onRowSelect ( SelectEvent<TodoItem> event ) {
        FacesMessage msg = new FacesMessage ("TodoItem Selected", event.getObject ().getId ().toString ());
        FacesContext.getCurrentInstance ().addMessage (null, msg);
    }

    public void onRowUnselect ( UnselectEvent<TodoItem> event ) {
        FacesMessage msg = new FacesMessage ("TodoItem Unselected", event.getObject ().getId ().toString ());
        FacesContext.getCurrentInstance ().addMessage (null, msg);
    }

    public String getName () {
        return name;
    }
    public void setName ( String name ) {
        this.name = name;
    }
    public String getCategory () {
        return category;
    }
    public void setCategory ( String category ) {
        this.category = category;
    }
}

