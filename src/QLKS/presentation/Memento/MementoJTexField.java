package QLKS.presentation.Memento;

import java.awt.event.ActionEvent;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MementoJTexField {
    private JTextField textField;
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    public MementoJTexField(JTextField jTextField){
        this.textField =jTextField;
        this.textField.getDocument().addDocumentListener(new DocumentListener() {

            private String previousText = textField.getText();
            @Override
            public void insertUpdate(DocumentEvent e) {
                undoStack.push(previousText);
                redoStack.clear();
                previousText = textField.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                undoStack.push(previousText);
                redoStack.clear();
                previousText = textField.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                undoStack.push(previousText);
                redoStack.clear();
                previousText = textField.getText();
            }
        });

        // Add key binding for undo
        this.textField.getInputMap().put(KeyStroke.getKeyStroke("ctrl Z"), "undo");
        this.textField.getActionMap().put("undo", new AbstractAction() {
            

            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

        // Add key binding for redo
        this.textField.getInputMap().put(KeyStroke.getKeyStroke("ctrl Y"), "redo");
        this.textField.getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
            textField.setText(undoStack.isEmpty() ? "" : undoStack.peek());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
            textField.setText(undoStack.peek());
        }
    }
}
