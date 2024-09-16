package com.example.express.Classes;

public class SeatSelectionModel {
   private String textView;
    private boolean isVisible;
    private boolean isSelected;

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SeatSelectionModel(String textView, boolean isVisible, boolean isSelected) {
        this.textView = textView;
        this.isVisible = isVisible;
        this.isSelected = isSelected;
    }
}
