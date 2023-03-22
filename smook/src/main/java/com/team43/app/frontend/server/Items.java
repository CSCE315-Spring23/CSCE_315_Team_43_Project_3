package com.team43.app.frontend.server;

import javax.swing.border.LineBorder;

import javax.swing.*;


import com.team43.app.backend.server.*;

public class Items extends JPanel {
/**
 * The ServerBackend instance associated with this Items panel.
 */
ServerBackend backend;

/**
 * The ServerPanel instance associated with this Items panel.
 */
ServerPanel mainPanel;

/**
 * Constructs a new Items panel with the given ServerBackend and ServerPanel instances.
 * Sets the layout to BoxLayout with PAGE_AXIS alignment and sets a LineBorder.
 *
 * @param sb The ServerBackend instance to associate with this Items panel.
 * @param sp The ServerPanel instance to associate with this Items panel.
 */
public Items(ServerBackend sb, ServerPanel sp) {
    backend = sb;
    mainPanel = sp;
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBorder(new LineBorder(getBackground(), 3));
}
}
