/**
 * The Classroom class represents a classroom with its name, shape, dimensions (width, length, height).
 */
public class Classroom {
    private String classroomName; // The name of the classroom
    private String classroomShape; // The shape of the classroom
    private int width; // The width of the classroom
    private int length; // The length of the classroom
    private int height; // The height of the classroom

    /**
     * Constructs a Classroom object with the specified name, shape, width, length, and height.
     *
     * @param classroomName The name of the classroom.
     * @param classroomShape The shape of the classroom.
     * @param width The width of the classroom.
     * @param length The length of the classroom.
     * @param height The height of the classroom.
     */
    public Classroom(String classroomName, String classroomShape, int width, int length, int height) {
        this.classroomName = classroomName;
        this.classroomShape = classroomShape;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    /**
     * Retrieves the name of the classroom.
     *
     * @return The name of the classroom.
     */
    public String getClassroomName(){
        return classroomName;
    }

    /**
     * Retrieves the shape of the classroom.
     *
     * @return The shape of the classroom.
     */
    public String getClassroomShape(){
        return classroomShape;
    }
    
    /**
     * Retrieves the width of the classroom.
     *
     * @return The width of the classroom.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the classroom.
     *
     * @return The height of the classroom.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retrieves the length of the classroom.
     *
     * @return The length of the classroom.
     */
    public double getLength() {
        return length;
    }
}

/**
 * The Circle class represents a type of classroom with a circular shape.
 */
class Circle extends Classroom{
    /**
     * Constructs a Circle object with the specified name, shape, width, length, and height.
     *
     * @param classroomName The name of the circular classroom.
     * @param classroomShape The shape of the circular classroom.
     * @param width The width of the circular classroom.
     * @param length The length of the circular classroom.
     * @param height The height of the circular classroom.
     */
    public Circle(String classroomName, String classroomShape, int width , int length, int height) {
        super(classroomName, classroomShape, width, length, height);
    }

    /**
     * Calculates the wall area of the circular classroom.
     *
     * @return The wall area of the circular classroom.
     */
    public double getWallArea() {
        return getHeight() * Math.PI * getWidth();
    }

    /**
     * Calculates the floor area of the circular classroom.
     *
     * @return The floor area of the circular classroom.
     */
    public double getFloorArea(){
        return ((getWidth()*getWidth()) / 4) * Math.PI;
    }
}

/**
 * The Rectangle class represents a type of classroom with a rectangular shape.
 */
class Rectangle extends Classroom{
    /**
     * Constructs a Rectangle object with the specified name, shape, width, length, and height.
     *
     * @param classroomName The name of the rectangular classroom.
     * @param classroomShape The shape of the rectangular classroom.
     * @param width The width of the rectangular classroom.
     * @param length The length of the rectangular classroom.
     * @param height The height of the rectangular classroom.
     */
    public Rectangle(String classroomName, String classroomShape, int width, int length, int height) {
        super(classroomName, classroomShape, width, length, height);
    }

    /**
     * Calculates the wall area of the rectangular classroom.
     *
     * @return The wall area of the rectangular classroom.
     */
    public double getWallArea() {
        return 2 * getHeight() * (getLength() + getWidth());
    }

    /**
     * Calculates the floor area of the rectangular classroom.
     *
     * @return The floor area of the rectangular classroom.
     */
    public double getFloorArea() {
        return getLength() * getWidth();
    }
}
