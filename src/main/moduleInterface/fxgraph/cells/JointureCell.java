package fxgraph.cells;

import fxgraph.graph.Graph;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class JointureCell extends AbstractCell {

    String leftJointureText;

    String rightJointureText;

    StackPane jointureGraph = new StackPane();

    public JointureCell(String text) {
        System.out.println(text);
        String[] separated = text.split("=");
        this.leftJointureText = separated[0];
        this.rightJointureText = separated[1];
    }

    @Override
    public Region getGraphic(Graph graph) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/tree_icons/jointure.fxml")));
            Group jointureGroup = (Group) root.lookup("#jointureGroup");
            Label jointureLeftLabel = (Label) jointureGroup.lookup("#jointureLeftLabel");
            Label jointureRightLabel = (Label) jointureGroup.lookup("#jointureRightLabel");

            double jointureLeftTextWidth = new Text(this.leftJointureText).getLayoutBounds().getWidth();

            jointureLeftLabel.setText(this.leftJointureText);
            jointureRightLabel.setText(this.rightJointureText);

            jointureLeftLabel.setLayoutX(jointureLeftLabel.getLayoutX()-jointureLeftTextWidth);

            String styles =
                "-fx-font-weight: 700;" +
                "-fx-text-fill: black;" ;
            jointureLeftLabel.setStyle(styles);
            jointureRightLabel.setStyle(styles);

            this.jointureGraph.getChildren().add(jointureGroup);

            return this.jointureGraph;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setSelected(boolean select) {
        // TODO Auto-generated method stub
    }

}
