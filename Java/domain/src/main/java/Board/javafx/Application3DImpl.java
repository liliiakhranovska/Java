package Board.javafx;

import Board.javafx.windowconfig.*;
import Board.logic.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Application3DImpl extends Application implements Application3D {

    private Environment env;

    public Application3DImpl() {
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Type of GAME:" + Game.getInstance().getType());
        switch (Game.getInstance().getType()) {
            case SINGLEBOARD:
                SINGLEBOARD.create(primaryStage);
                break;
            case DOUBLEBOARD:
                DOUBLEBOARD.create(primaryStage);
                break;
//            case SWEDISH:
//                System.out.println("SWEDISH");
//                Stage stage4 = new Stage();
//                stage4.show();
//                Stage stage5 = new Stage();
//                stage5.show();
//                env = new Environment3DImpl(stage4);
//                env = new Environment3DImpl(stage5);
//                break;
        }
    }

    @Override
    public void main(String[] args) {
        Application.launch(args);
        /*launch is static method*/
    }

}
