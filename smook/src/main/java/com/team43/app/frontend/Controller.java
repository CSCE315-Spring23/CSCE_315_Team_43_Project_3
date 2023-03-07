package com.team43.app.frontend;

import java.util.Stack;
import javax.swing.JPanel;

public class Controller {
    Stack pages;
    JPanel currentPage;

    Controller() {
        pages = new Stack<JPanel>();
    }

    public void navigatePage(JPanel panel) {
        if (currentPage != null) {
            // Hide this page and push it
            currentPage.setVisible(false);
            pages.push(currentPage);
        }
        currentPage = panel;
        currentPage.setVisible(true);
    }

    public JPanel navigatePageBack() {
        pages.pop().setVisible(true);
    }
}
