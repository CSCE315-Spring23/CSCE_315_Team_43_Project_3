package com.team43.app.frontend.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import com.team43.app.backend.server.*;

public class Items extends JPanel {
    ServerBackend backend;
    ServerPanel mainPanel;
    public Items(ServerBackend sb, ServerPanel sp) {
        backend = sb;
        mainPanel = sp;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new LineBorder(getBackground(), 3));
    }
}
