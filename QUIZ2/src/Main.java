public class Main1 {
    public static void main(String[] args) {
        
        BuildingBuilder classroomBuilder = new ClassroomConcreteBuilder();
        BuildingBuilder decorationBuilder = new DecorationConcreteBuilder();

        ((ClassroomConcreteBuilder) classroomBuilder).buildClassroom(args[0]);
        ((DecorationConcreteBuilder) decorationBuilder).buildDecoration(args[1]);

        BuildingBuilder buildingBuilder = new BuildingConcreteBuilder(args[2], classroomBuilder, decorationBuilder);
        ((BuildingConcreteBuilder) buildingBuilder).build();


    }
}
