import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * PainterController Class.
 * Enables the user to paint on the pane, while picking the desired shape, its color, 
 * and position.
 */
public class PainterController {
	// enum type used to indicate the shape
	private enum Shape {LINE, RECTANGLE, ELLIPSE}
	
	// enum type used to indicate whether a shape should be filled or outlined
	private enum Fill { FILL, BORDER_LINE}
	
	// instance variables that refer to GUI components
	// color radioButton
	@FXML private RadioButton lemonChiffonRadioButton;// radioButton
	@FXML private RadioButton peachPuffButton;        // radioButton
	@FXML private RadioButton mistyRoseRadioButton;   // radioButton
	@FXML private RadioButton lavenderRadioButton;    // radioButton
	@FXML private RadioButton lightBlueRadioButton;   // radioButton
    @FXML private ToggleGroup colorToggleGroup;       // toggleGroup
    
    // fill radioButton
    @FXML private RadioButton fillRadioButton;        // radioButton
    @FXML private RadioButton outLineRadioButton;     // radioButton
    @FXML private ToggleGroup fillToggleGroup;        // toggleGroup
    
    // shape radioButton
    @FXML private RadioButton lineRadioButton;        // radioButton
    @FXML private RadioButton rectangleRadioButton;   // radioButton
    @FXML private RadioButton ellipseRadioButton;     // radioButton
    @FXML private ToggleGroup shapeToggleGroup;       // toggleGroup

    @FXML private Pane drawingAreaPane;
    
    // instance variables for managing Painter state - default value
    private Shape shape = Shape.LINE;
    private Paint shapeColor = Color.LIGHTBLUE;
    private Paint fill = Color.LIGHTBLUE;
    private Fill fillType = Fill.FILL;
    private double mousePressedX = -1, mousePressedY = -1;

    /* set user data for the RadioButtons */
    public void initialize() {
    	// set color
    	lemonChiffonRadioButton.setUserData(Color.LEMONCHIFFON);
        peachPuffButton.setUserData(Color.PEACHPUFF);
    	mistyRoseRadioButton.setUserData(Color.MISTYROSE);
    	lavenderRadioButton.setUserData(Color.LAVENDER);
    	lightBlueRadioButton.setUserData(Color.LIGHTBLUE);
    	// set fill
    	fillRadioButton.setUserData(Fill.FILL);
    	outLineRadioButton.setUserData(Fill.BORDER_LINE);
    	//set shape
    	lineRadioButton.setUserData(Shape.LINE);
    	rectangleRadioButton.setUserData(Shape.RECTANGLE);
    	ellipseRadioButton.setUserData(Shape.ELLIPSE);
    	// selecting default buttons
    	lightBlueRadioButton.setSelected(true);
    	fillRadioButton.setSelected(true);
    	lineRadioButton.setSelected(true);
    }
	
	/* handles color RadioButton's ActionEvents */
    @FXML
    void colorRadioButtonSelected(ActionEvent event) {
    	// user data for each color RadioButton is the corresponding Color
    	shapeColor = ((Color) colorToggleGroup.getSelectedToggle().getUserData());
    	if (fillType == Fill.FILL) {fill = shapeColor;}
    	
    }
    
    /* handles fill RadioButton's ActionEvents */
    @FXML
    void fillRadioButtonSelected(ActionEvent event) {
    	fillType = ((Fill)fillToggleGroup.getSelectedToggle().getUserData());
        switch (fillType) {
            case FILL:
                fill = shapeColor;
                break;
            case BORDER_LINE:
                fill = null;
                break;
        }
    }

    /* handles shape RadioButton's ActionEvents */
    @FXML
    void shapeRadioButtonSelected(ActionEvent event) {
    	shape = (Shape) shapeToggleGroup.getSelectedToggle().getUserData();
    }

    /* handles Undo Button's ActionEvent */
    @FXML
    void undoButtonSelected(ActionEvent event) {
    	int count = drawingAreaPane.getChildren().size();
    	
    	// if there are any shapes remove the last one added
    	if (count > 0)
    		drawingAreaPane.getChildren().remove(count-1);
    }
    
    /* handles Clear Button's ActionEvent */
    @FXML
    void clearButtonSelected(ActionEvent event) {
		drawingAreaPane.getChildren().clear();
    }
    
    /* handles drawingArea's onMousePressed MouseEvent */
    @FXML
    void drawingAreaMousePressed(MouseEvent event) {
    	mousePressedX = event.getX();
    	mousePressedY = event.getY();
    }

    /* handles drawingArea's MouseReleased MouseEvent */
    @FXML
    void drawingAreaMouseReleased(MouseEvent event) {
		switch (shape) {
		case LINE:
			// create new Line object with the events coordinate
			Line line = new Line(mousePressedX, mousePressedY, event.getX(), event.getY());
			// paint with the accurate color
			line.setStroke(shapeColor);
			// add object to pane
			drawingAreaPane.getChildren().add(line);
			break;
			
		case RECTANGLE:
			// create new Rectangle object
			Rectangle rectangle = new Rectangle();
			// determine the position of the upper left corner of the shape
	    	double leftCornerX = Math.min(event.getX(), mousePressedX);
	    	double leftCornerY = Math.min(event.getY(), mousePressedY);
	    	// position the rectangle
			rectangle.setX(leftCornerX);
			rectangle.setY(leftCornerY);
			rectangle.setWidth(Math.abs(mousePressedX- event.getX()));
			rectangle.setHeight(Math.abs(mousePressedY- event.getY()));
			// paint with the accurate color/fill
			rectangle.setStroke(shapeColor);
	    	rectangle.setFill(fill);
	    	// add object to pane
			drawingAreaPane.getChildren().add(rectangle);
			break;
			
		case ELLIPSE:
			// create new Ellipse object
			Ellipse ellipse = new Ellipse();
			// position the Ellipse according to events coordinate
			ellipse.setCenterX((mousePressedX + event.getX()) / 2.0);
			ellipse.setCenterY((mousePressedY + event.getY()) / 2.0);
			ellipse.setRadiusX(Math.abs(event.getX() - mousePressedX) / 2.0);
			ellipse.setRadiusY(Math.abs(event.getY() - mousePressedY) / 2.0);
			// paint with the accurate color/fill
			ellipse.setStroke(shapeColor);
			ellipse.setFill(fill);
			// add object to pane
			drawingAreaPane.getChildren().add(ellipse);
		default: // do not draw any shape
    	}
    }
} // end of class PainterController

