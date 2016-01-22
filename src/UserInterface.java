import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class UserInterface extends Application {

    public Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        Font font = new Font(25);
        Label a = new Label("");
        a.setFont(font);
        Label aName = new Label("A: ");
        aName.setFont(font);
        Label b = new Label("");
        b.setFont(font);
        Label bName = new Label("B: ");
        bName.setFont(font);
        Label offset = new Label();
        offset.setFont(font);
        Label oName = new Label("Offset: ");
        oName.setFont(font);
        TextField aph = new TextField();
        aph.setPromptText("Aphelion/Apoapsis");
        TextField per = new TextField();
        per.setPromptText("Perihelion/Periapsis");
        TextField ecc = new TextField();
        ecc.setPromptText("Eccentricity");
        TextField rad = new TextField();
        rad.setPromptText("Radius of Orbited Body");
        Button calc = new Button("Calculate");
        calc.setOnAction(event -> {
            String[] values = calculateValues(aph.getText(),
                per.getText(), ecc.getText(), rad.getText());
            a.setText(values[0]);
            b.setText(values[1]);
            offset.setText(values[2]);
            aph.setText("");
            per.setText("");
            ecc.setText("");
            rad.setText("");
        });

        VBox input = new VBox();
        input.getChildren().addAll(aph, per, ecc, rad, calc);
        input.setPrefWidth(300);
        HBox theA = new HBox();
        theA.getChildren().addAll(aName, a);
        HBox theB = new HBox();
        theB.getChildren().addAll(bName, b);
        HBox theOffset = new HBox();
        theOffset.getChildren().addAll(oName, offset);
        VBox output = new VBox();
        output.getChildren().addAll(theA, theB, theOffset);
        output.setPrefWidth(400);

        HBox main = new HBox();
        main.getChildren().addAll(input, output);
        Scene oec = new Scene(main);
        primaryStage.setScene(oec);
        primaryStage.setTitle("Orbit Equation Calculator");
        primaryStage.setHeight(500);
        primaryStage.setWidth(700);
        primaryStage.show();
    }

    public String[] calculateValues(String aph, String per,
            String ecc, String oRad) {
        String[] valuez = new String[3];
        double aphel = Double.valueOf(aph);
        double perih = Double.valueOf(per);
        double eccen = Double.valueOf(ecc);
        double obRad = Double.valueOf(oRad);
        OrbitEquationCalculator oec =
            new OrbitEquationCalculator(aphel, perih, eccen, obRad);
        valuez[0] = String.format("%.0f", Double.toString(oec.getTheA()));
        valuez[1] = String.format("%.0f", Double.toString(oec.getTheB()));
        valuez[2] =
            String.format("%.0f", Double.toString(oec.getTheOffset()));
        return valuez;
    }

}
